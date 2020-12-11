package com.aarria.retail.core.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aarria.retail.core.domain.Address;
import com.aarria.retail.core.domain.Cart;
import com.aarria.retail.core.domain.Delivery;
import com.aarria.retail.core.domain.Item;
import com.aarria.retail.core.domain.Log;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.Payment;
import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductStock;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.domain.Wallet;
import com.aarria.retail.core.service.AddressService;
import com.aarria.retail.core.service.BankAccountService;
import com.aarria.retail.core.service.CartService;
import com.aarria.retail.core.service.ItemService;
import com.aarria.retail.core.service.LogService;
import com.aarria.retail.core.service.MessageService;
import com.aarria.retail.core.service.OfferService;
import com.aarria.retail.core.service.OrderService;
import com.aarria.retail.core.service.ProductService;
import com.aarria.retail.core.service.ProductStockService;
import com.aarria.retail.core.service.UserService;
import com.aarria.retail.core.service.WalletService;
import com.aarria.retail.core.util.Constants;
import com.aarria.retail.core.util.DateComparator;
import com.aarria.retail.core.util.Enum;
import com.aarria.retail.core.util.Enum.Activity;
import com.aarria.retail.core.util.Enum.ItemStatus;
import com.aarria.retail.core.util.Enum.OrderStatus;
import com.aarria.retail.core.util.Enum.PaymentMethod;
import com.aarria.retail.core.util.Enum.PlaceOrderFailReason;
import com.aarria.retail.core.util.Enum.ReturnCashMethod;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.persistence.repository.OrderRepository;
import com.aarria.retail.web.dto.request.ApproveReturnDto;
import com.aarria.retail.web.dto.request.CancelOrderDto;
import com.aarria.retail.web.dto.request.OrderDetailDto;
import com.aarria.retail.web.dto.request.RefundDto;
import com.aarria.retail.web.dto.request.RemoveItemFromOrderDto;
import com.aarria.retail.web.dto.request.ReturnOrderSubmitDto;
import com.aarria.retail.web.dto.response.AddressDto;
import com.aarria.retail.web.dto.response.BankAccountDetailsDto;
import com.aarria.retail.web.dto.response.OrderDetailOutputDto;
import com.aarria.retail.web.dto.response.PaymentSuccessDTO;
import com.aarria.retail.web.dto.response.PrepareOrderResultDto;
import com.aarria.retail.web.dto.response.RefundDetailsDto;
import com.aarria.retail.web.dto.response.ReturnOrderDetailsDto;
import com.aarria.retail.web.dto.response.ReturnOrderItemDetailsDto;
import com.aarria.retail.web.dto.response.ReturnOrderSubmitResultDto;
import com.aarria.retail.web.dto.response.SoldoutProductDto;

@Service
public class OrderServiceImpl implements OrderService {

	private static Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductStockService productStockService;

	@Autowired
	private LogService logService;

	@Autowired
	private CartService cartService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private OfferService offerService;

	@Override
	public Order findByOrderNo(String orderNo) {
		return orderRepository.findByOrderId(orderNo);
	}

	@Override
	public boolean cancelOrder(CancelOrderDto dto) {
		Order order = orderRepository.findOneWithItems(dto.getOrderIdPrimaryKey());

		if (order != null && order.getStatus().equals(OrderStatus.DISPATCHED.ordinal())) {
			return false;
		}

		if (Util.isMoreThanThirtyMins(order.getOrderedDate())
				&& !(dto.getFromPage() != null && dto.getFromPage().contains(Constants.VIEW_ORDER_DETAILS))) {
			return false;
		}

		if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
			for (Item item : order.getOrderedItems()) {

				if (item.getStatus().equals(ItemStatus.OPEN.ordinal())) {
					itemService.addProductStockBackOnRemoveItem(item);
				}

				item.setStatus(ItemStatus.OPEN.ordinal());
				item.setReturnCashMethod(null);
			}
		}

		if (dto.getFromPage().contains(Constants.MYORDERS)) {
			order.setStatus(Enum.OrderStatus.CANCELLED_WITHIN_30_MINS.ordinal());
		} else if (dto.getFromPage().contains(Constants.VIEW_ORDER_DETAILS)) {
			order.setStatus(Enum.OrderStatus.CANCELLED_WHILE_PACKAGING.ordinal());
		}

		// Means it's Non-COD
		if (order.getIsCashOnDelivery() != null && order.getIsCashOnDelivery().equals(false)) {
			order.setStatus(OrderStatus.REFUND_INITIATED.ordinal());
			order.setReturnCashMethod(dto.getReturnCashMethod());

			if (dto.getReturnCashMethod().equals(ReturnCashMethod.BANK_ACCOUNT.ordinal())
					&& dto.getSelectedAccountForRefund() != null) {
				order.setSelectedAccountForRefund(dto.getSelectedAccountForRefund());
			}

			messageService.sendEmailAndSmsToAdmin("Non-COD Order " + order.getOrderId() + " is cancelled",
					"NON-COD Order cancelled");
		}

		User user = order.getUser();
		if (user == null) {
			throw new RuntimeException("Unable to cancel order - user is not present");
		}

		messageService.sendCancelledOrderMessage(order, user);
		order.setComments(dto.getComments());
		save(order);
		return true;
	}

	@Override
	public List<Order> getOrders(Long userId, Integer status) {
		User user = new User();
		user.setId(userId);
		return orderRepository.findByUserAndStatus(user, status);
	}

	@Override
	public List<Order> getOrders(Long userId) {
		User user = new User();
		user.setId(userId);
		return orderRepository.findByUser(user);
	}

	@Override
	public PaymentSuccessDTO getPaymentSuccessDTO(String orderNo, User user) {

		LOGGER.info("After payment success bulding payment success dto for order no " + orderNo + " for user " + user);

		Order order = findByOrderNo(orderNo);

		if (order == null) {
			LOGGER.info("order is null after the payment");
			throw new RuntimeException("Unable to find order for order no " + orderNo
					+ " after payment is success. Please call customer care.");
		}

		Delivery delivery = order.getDelivery();

		if (delivery == null) {
			LOGGER.info("delivery is null after the payment");
			throw new RuntimeException("Unable to find delivery for orderNo " + orderNo
					+ " after payment is success. Please call customer care.");
		}

		Address shippingAddress = delivery.getShippingAddress();

		if (shippingAddress == null) {
			LOGGER.info("shippingAddress is null after the payment");
			throw new RuntimeException(
					"Unable to find shipping address after payment is success. Please call customer care.");
		}

		PaymentSuccessDTO dto = new PaymentSuccessDTO(orderNo, shippingAddress.getMobile(), user.getEmail(),
				Util.formatEstimatedDeliveryDate(order), order.getId());
		LOGGER.info("successfully completed getPaymentSuccessDTO");
		return dto;
	}

	@Transactional
	public PrepareOrderResultDto buildOrder(Set<Cart> carts, User user, Integer paymentMode) {
		PrepareOrderResultDto result = new PrepareOrderResultDto();

		Order order = new Order();
		Map<String, Product> productsCache = fetchCartProducts(carts);

		double total = 0;
		int totalQuantity = 0;

		List<ProductStock> stocks = new ArrayList<>();
		for (Cart cart : carts) {
			Product product = cart.getProduct();

			if (product == null) {
				result.setReason(PlaceOrderFailReason.PRODUCT_COUNT_MISMATCH.name());
				return result;
			}

			product = productsCache.get(product.getPid());
			if (product == null) {
				logProduct(user, cart.getProduct().getId());
				result.setReason(PlaceOrderFailReason.PRODUCT_COUNT_MISMATCH.name());
				return result;
			}

			ProductStock productStock = getStockForSize(product, cart.getSize());
			if (productStock == null || productStock.getStock() == null || productStock.getStock() <= 0
					|| cart.getQuantity() > productStock.getStock()) {

				logProduct(user, cart.getProduct().getId());
				result.setReason(PlaceOrderFailReason.PRODUCT_COUNT_MISMATCH.name());
				return result;
			}

			int quantity = cart.getQuantity();
			int stock = productStock.getStock();

			if (quantity > stock) {
				quantity = stock;
				logQuantity(user, stock, quantity);
			}

			int newStock = stock - quantity;

			if (newStock == 0) {
				// stockIterator.remove(); //DO NOT remove make stock as 0
				productStock.setStock(0);
			} else {
				productStock.setStock(newStock);
			}

			cart.setProduct(product);

			if (stock == 0) {
				String activity = Activity.SIZE_JUST_OLD_OUT.name() + " size = " + cart.getSize();
				Log moreOrderThanStockLog = new Log(user, activity, new Date());
				logService.save(moreOrderThanStockLog);

				continue;
			}

			double itemTotalPrice = (product.getPrice() * quantity);

			stocks.add(productStock);

			total += addItem(user, order, cart, quantity, itemTotalPrice);
			totalQuantity += quantity;

		}

		order.setIsCashOnDelivery(isCashOnDelivery(paymentMode));
		useWallet(user, paymentMode, order, total);
		applyOfferForWholeOrder(order, total, totalQuantity);

		Order placedOrder = checkItemAvailabilityAndSaveOrder(order, stocks, paymentMode);

		if (placedOrder == null) {
			result.setReason(PlaceOrderFailReason.PRODUCT_COUNT_MISMATCH.name());
			return result;
		}

		result.setOrder(placedOrder);

		updateOrderId(placedOrder);
		return result;
	}

	private void useWallet(User user, Integer paymentMode, Order order, double total) {
		Wallet wallet = null;//walletService.findByUser(user);
		if (wallet != null && wallet.getAmount() > 0) {
			order.setWalletAmountUsed(wallet.getAmount());
			total = total - wallet.getAmount();
			walletService.deductAmount(wallet.getAmount(), user, order);
		}

		order.setPayment(new Payment(order, user, total, paymentMode));
	}

	private ProductStock getStockForSize(Product product, String size) {
		if (CollectionUtils.isNotEmpty(product.getProductStock())) {
			return product.getProductStock().stream().filter(s -> s.getSize().equals(size)).findFirst().orElse(null);

		}
		return null;
	}

	private void updateOrderId(Order placedOrder) {
		placedOrder.setOrderId(Util.generateRandomOrderId(placedOrder.getId()));
		save(placedOrder);
	}

	/**
	 * After the order is retrieved from cart and processed, if all are not
	 * available let the user decide
	 * 
	 * @param order
	 * @param stocks
	 * @param paymentMode
	 * @return
	 */
	@Transactional
	private synchronized Order checkItemAvailabilityAndSaveOrder(Order order, List<ProductStock> stocks,
			Integer paymentMode) {
		if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
			for (Item item : order.getOrderedItems()) {
				Product p = item.getProduct();
				if (p == null) {
					return null;
				}

				ProductStock stock = productStockService.findStockByProductAndSize(p, item.getSize());
				if (stock == null) {
					return null;
				}

				if (stock.getStock() == null || item.getQuantity() == null) {
					return null;
				}

				if (stock.getStock() < item.getQuantity()) {
					return null;
				}
			}
		}

		if (paymentMode.equals(PaymentMethod.NON_COD.ordinal())) {
			order.setStatus(OrderStatus.NON_COD_PAYMENT_NOT_DONE.ordinal());
		} else {
			order.setStatus(OrderStatus.COD_NOT_CONFIRMED.ordinal());
		}

		Order placedOrder = save(order);
		productStockService.save(stocks);
		return placedOrder;
	}

	private void applyOfferForWholeOrder(Order order, double total, int totalQuantity) {
		order.setTotalOrderAmount(offerService.availOfferForWholeOrder(total, totalQuantity));
	}

	private Map<String, Product> fetchCartProducts(Set<Cart> carts) {
		Map<String, Product> products = new HashMap<>();
		if (CollectionUtils.isNotEmpty(carts)) {
			for (Cart cart : carts) {
				if (cart.getProduct() != null) {

					String pid = cart.getProduct().getPid();

					if (products.get(pid) == null) {
						Product product = productService.findByPidWithStock(pid);
						products.put(pid, product);
					}
				}
			}
		}

		return products;
	}

	private double addItem(User user, Order order, Cart c, int quantity, double itemTotalPrice) {
		Item item = c.createOrderedItem(order);

		order.getOrderedItems().add(item); // save item with order

		order.setDelivery(buildDelivery(user));
		order.setUser(user);
		order.setOrderedDate(new Date());

		item.setTotalSoldPrice(itemTotalPrice);

		item.setQuantity(quantity);
		return itemTotalPrice;

	}

	private boolean isCashOnDelivery(Integer paymentMode) {
		if (paymentMode.equals(PaymentMethod.COD.ordinal())) {
			return true;
		}

		return false;
	}

	private void logProduct(User user, Long productId) {
		String activity = Activity.OLD_PRODUCT.name() + " product = " + productId;
		Log moreOrderThanStockLog = new Log(user, activity, new Date());
		logService.save(moreOrderThanStockLog);
	}

	private void logQuantity(User user, int stock, int quantity) {
		String activity = Activity.ORDER_IS_GREATER_THAN_STOCK.name() + ", stock = " + stock + " , quantity = "
				+ quantity;
		Log moreOrderThanStockLog = new Log(user, activity, new Date());
		logService.save(moreOrderThanStockLog);

		quantity = stock;
	}

	private Delivery buildDelivery(User user) {
		Delivery delivery = new Delivery();
		delivery.setShippingAddress(addressService.getDeliveryAddress(user));
		delivery.setExpectedDeliveryDate(calculateDeliveryDate());
		delivery.setOrderedDate(new Date());
		// TODO make different
		delivery.setBillingAddress(addressService.getDeliveryAddress(user));

		return delivery;
	}

	private Date calculateDeliveryDate() {

		int hoursToAdd = 18;
		int daysToAdd = 3;

		if (Util.isWeekEnd()) {
			hoursToAdd = 6;
			daysToAdd = 0;
		}

		ZoneId indiaZone = ZoneId.of(ZoneId.SHORT_IDS.get("IST"));
		ZonedDateTime zonedDateTime = LocalDateTime.now(indiaZone).withHour(hoursToAdd).withMinute(0).withSecond(0)
				.plusDays(daysToAdd).atZone(indiaZone);
		Date date = Date.from(zonedDateTime.toInstant());
		return date;
	}

	@Override
	public Order findOneWithItems(Long id) {
		return orderRepository.findOneWithItems(id);
	}

	@Override
	public Order findOneWithItems(String orderId, User user) {
		return orderRepository.findOneWithItems(orderId, user);
	}

	@Override
	public Order findOneById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public Order save(Order order) {
		try {
			return orderRepository.save(order);
		} catch (Exception e) {
			messageService.sendEmailToAdmin(Util.retrieveStackTraceFromException(e), "Unable to save order");
			return null;
		}
	}

	@Override
	public List<OrderDetailOutputDto> getOrders(OrderDetailDto dto) {
		List<OrderDetailOutputDto> orders = new ArrayList<>();
		if (StringUtils.isNotEmpty(dto.getOrderno())) {
			Order order = orderRepository.findByOrderId(dto.getOrderno());
			if (order != null) {
				OrderDetailOutputDto out = new OrderDetailOutputDto();
				out.setOrderIdPrimaryKey(order.getId() + "");
				out.setOrderNo(order.getOrderId());

				orders.add(out);
			}

		} else if (dto.getMobile() != null) {
			User user = userService.findUserByMobile(dto.getMobile(), Constants.VERIFIED);
			if (user != null) {
				List<Order> orderByUser = getOrders(user.getId());
				if (CollectionUtils.isNotEmpty(orderByUser)) {
					for (Order o : orderByUser) {
						OrderDetailOutputDto out = new OrderDetailOutputDto();
						out.setOrderIdPrimaryKey(o.getId() + "");
						out.setOrderNo(o.getOrderId());
						orders.add(out);
					}
				}
			}
		}

		return orders;
	}

	@Override
	public boolean removeItemFromOrder(RemoveItemFromOrderDto dto) {
		Order order = findOneWithItems(dto.getOrderIdPrimaryKey());

		if (Util.isMoreThanThirtyMins(order.getOrderedDate())) {
			return false;
		}

		order = itemService.removeItemFromOrder(dto, order);

		if (order == null) {
			return false;
		}

		if (isOrderCancelledOnSingleItemRemoval(order)) {
			CancelOrderDto cancelDto = new CancelOrderDto(order.getId(), dto.getFromPage(), dto.getReturnCashMethod(),
					dto.getSelectedAccountForRefund(), "Remove item");

			cancelOrder(cancelDto);

		} else {
			save(order);
		}

		return true;
	}

	private boolean isOrderCancelledOnSingleItemRemoval(Order order) {
		return order.getStatus().equals(OrderStatus.CANCELLED_WHILE_PACKAGING.ordinal())
				|| order.getStatus().equals(OrderStatus.CANCELLED_WITHIN_30_MINS.ordinal());
	}

	@Override
	public List<RefundDetailsDto> getCancelRefunds() {
		List<Order> orders = orderRepository.getAllRefundsForOrder(Util.getCancelRefundStatusOrdinalsForOrders());
		List<Order> ordersByItem = orderRepository.getOrderByItemStatus(Util.getCancelRefundStatusOrdinalsForItems());

		if (CollectionUtils.isNotEmpty(ordersByItem)) {
			orders.addAll(ordersByItem);
		}

		orders.sort(new DateComparator());

		List<RefundDetailsDto> refunds = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(orders)) {
			for (Order order : orders) {
				RefundDetailsDto dto = new RefundDetailsDto(order.getOrderId(), order.getId() + "",
						order.getOrderedDate() + "", null, OrderStatus.getString(order.getStatus()));
				if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
					dto.setTotalAmount(getTotalAmountToBeRefunded(order));
				}

				refunds.add(dto);
			}
		}

		return refunds;
	}

	// Return custom messages
	@Override
	public String refundOrder(RefundDto dto) {

		String message = "";

		Order order = findOneWithItems(dto.getOrderIdPrimaryKey());

		User user = order.getUser();

		if (user == null) {
			throw new RuntimeException("User is null while refunding for order id primary key " + order.getId());
		}

		if (isValidRefundForOrder(order, dto)) {
			doRefundForOrder(order, user, dto);
		} else if (isValidRefundForItems(order, dto)) {
			doRefundForItems(order, user, dto);
		}

		if (order != null && order.getStatus() != null && order.getStatus().equals(OrderStatus.DELIVERED.ordinal())) {
			if (!isAnyOfItemsOpen(order)) {
				order.setStatus(OrderStatus.REFUNDED.ordinal());
			}
		}

		save(order);

		return message;
	}

	private boolean isAnyOfItemsOpen(Order order) {
		boolean isOpen = false;
		for (Item item : order.getOrderedItems()) {
			if (item.getStatus().equals(ItemStatus.OPEN.ordinal())) {
				isOpen = true;
			}
		}

		return isOpen;
	}

	private void doRefundForItems(Order order, User user, RefundDto dto) {
		transferMoney(order, user, dto);
		updateItemStatusAfterRefund(order);
	}

	private void updateItemStatusAfterRefund(Order order) {
		for (Item item : order.getOrderedItems()) {
			if (item.getStatus().equals(ItemStatus.REFUND_INITIATED.ordinal())) {
				item.setStatus(ItemStatus.REFUNDED.ordinal());
			}
		}
	}

	private boolean isValidRefundForItems(Order order, RefundDto dto) {
		if (Util.isValidRefundStatusForItems(order)) {
			if (isValidAmount(order, dto)) {
				return true;
			}
		}

		return false;
	}

	private void doRefundForOrder(Order order, User user, RefundDto dto) {
		transferMoney(order, user, dto);
		order.setStatus(OrderStatus.REFUNDED.ordinal());
		order.setTotalRefundedAmount(dto.getTotalAmountToBeRefundedForOrder());
		order.setRefundReceiptNumber(dto.getRefundReceiptNumber());
	}

	private void transferMoney(Order order, User user, RefundDto dto) {
		Integer returnMethod = Util.getReturnCashMethod(order);
		if (returnMethod != null && returnMethod.equals(ReturnCashMethod.WALLET.ordinal())) {
			walletService.depositAmount(dto.getTotalAmountToBeRefundedForOrder(), user, order);
		} else {
			messageService.sendMoneyDepositedToBankMessage(user, dto.getTotalAmountToBeRefundedForOrder(), order);
			// TODO do in the bank account
		}
	}

	private boolean isValidRefundForOrder(Order order, RefundDto dto) {
		if (Util.isValidRefundStatusForOrder(order)) {
			if (isValidAmount(order, dto)) {
				return true;
			}
		}

		return false;
	}

	private boolean isValidAmount(Order order, RefundDto dto) {
		if (dto.getTotalAmountToBeRefundedForOrder() > getTotalAmountToBeRefunded(order)) {
			return false;
		}

		return true;
	}

	private double getTotalAmountToBeRefunded(Order order) {
		double totalAmountTobeRefunded = 0d;
		// do NOT include refund status here
		for (Item item : order.getOrderedItems()) {
			if (getAdminRefundAmountItemAndOrderStatus(order, item)) {
				totalAmountTobeRefunded += item.getTotalSoldPrice();
			}
		}

		return totalAmountTobeRefunded;
	}

	private boolean getAdminRefundAmountItemAndOrderStatus(Order order, Item item) {
		if (order.getStatus().equals(OrderStatus.REFUND_INITIATED.ordinal())) {
			return true;
		}

		if (item.getStatus().equals(ItemStatus.REFUND_INITIATED.ordinal())) {
			return true;
		}

		return false;
	}

	@Override
	public ReturnOrderDetailsDto getReturnOrderDetails(String orderId, User user) {
		Order order = findOneWithItems(orderId, user);

		if (order == null || !order.getStatus().equals(OrderStatus.DELIVERED.ordinal())) {
			return null;
		}

		ReturnOrderDetailsDto dto = new ReturnOrderDetailsDto();

		if (Util.isItemsAreEligibleForReturn(order) == false) {
			return null;
		}

		if (order != null && CollectionUtils.isNotEmpty(order.getOrderedItems())) {

			if (user != null) {
				List<BankAccountDetailsDto> bankAccounts = bankAccountService.getAllBankAccountsForUser(user);
				dto.setBankAccounts(bankAccounts);

				List<AddressDto> addressDtos = Util.prepareAddresses(addressService.findByUser(user));
				dto.setAddresses(addressDtos);
			}

			for (Item item : order.getOrderedItems()) {
				if (Util.getReturnStatusForItems().contains(item.getStatus())) {
					ReturnOrderItemDetailsDto r = ReturnOrderItemDetailsDto.createFromProduct(item.getProduct(), item);
					dto.getItems().add(r);
				}
			}

			dto.setOrderId(order.getOrderId());
		}

		return dto;
	}

	@Override
	public ReturnOrderSubmitResultDto placeReturnOrder(ReturnOrderSubmitDto dto, User user) {

		Order order = findOneWithItems(dto.getOrderId(), user);
		if (order != null) {
			if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
				if (isEntireOrderCancelled(order, dto)) {
					order.setStatus(OrderStatus.RETURN_PICKUP_REQUESTED.ordinal());
				} else {
					doPartialReturn(order, dto);
				}

				messageService.sendReturnOrderPlacedMessage(user, order);

				updateOrderReturnDetails(dto, order);

			}

			save(order);
		}

		return null;
	}

	private void updateOrderReturnDetails(ReturnOrderSubmitDto dto, Order order) {
		order.setPickupDateAndTime(dto.getPickupDate() + " " + dto.getPickupTime());
		order.setReturnPickupAddress(dto.getPickupAddress());
		order.setReturnedDate(new Date());
		order.setReturnCashMethod(dto.getReturnCashMethod());
		order.setSelectedAccountForRefund(dto.getSelectedAccountForRefund());
		order.setIsDelivered(true);
	}

	private void doPartialReturn(Order order, ReturnOrderSubmitDto dto) {
		for (Item item : order.getOrderedItems()) {
			if (dto.getItemsToReturn().contains(item.getId())) {
				item.setStatus(ItemStatus.RETURN_PICKUP_REQUESTED.ordinal());
				order.setIsAnyOfTheItemIsReturned(true);
			}
		}
	}

	private boolean isEntireOrderCancelled(Order order, ReturnOrderSubmitDto dto) {
		boolean isEntireOrderCancelled = false;

		if (dto.getItemsToReturn() != null && dto.getItemsToReturn().size() == order.getOrderedItems().size()) {
			isEntireOrderCancelled = true;
		}

		return isEntireOrderCancelled;
	}

	@Override
	public void arrangeReturn(ApproveReturnDto dto) {
		Order order = findOneWithItems(dto.getOrderIdPrimaryKey());
		if (order != null) {
			if (order.getStatus().equals(OrderStatus.RETURN_PICKUP_REQUESTED.ordinal())) {
				// order.setStatus(OrderStatus.REFUND_INITIATED.ordinal());
			} else {
				if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
					for (Item item : order.getOrderedItems()) {
						if (item.getStatus().equals(ItemStatus.RETURN_PICKUP_REQUESTED.ordinal())) {
							// item.setStatus(ItemStatus.REFUND_INITIATED.ordinal());
						}
					}
				}
			}

			Delivery delivery = order.getDelivery();
			delivery.setReturnCourierServiceName(dto.getReturnCourierServiceName());
			delivery.setReturnTrackingId(dto.getReturnTrackingId());
			delivery.setReturnShippingCharge(dto.getReturnShippingCharge());
			delivery.setIsReturnShippingNotAvailable(dto.getIsReturnShippingNotAvailable());

			if (StringUtils.isNotBlank(dto.getReturnPickupAddress())) {
				order.setReturnPickupAddress(dto.getReturnPickupAddress());
			}

			if (StringUtils.isNotBlank(dto.getReturnPickupDateAndTime())) {
				order.setPickupDateAndTime(dto.getReturnPickupDateAndTime());
			}

			order.setIsReturnPickupArranged(true);

			User user = order.getUser();
			if (user == null) {
				throw new RuntimeException("Unable to arrange return user is null whole arranging return");
			}

			messageService.sendReturnPickupMessage(order, user);
			save(order);
		}
	}

	@Override
	public void approveReturn(ApproveReturnDto dto) {
		Order order = findOneWithItems(dto.getOrderIdPrimaryKey());
		if (order != null) {
			if (order.getStatus().equals(OrderStatus.RETURN_PICKUP_REQUESTED.ordinal())) {
				order.setStatus(OrderStatus.REFUND_INITIATED.ordinal());

				if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
					for (Item item : order.getOrderedItems()) {
						itemService.addProductStockBackOnRemoveItem(item);
					}
				}

			} else {
				if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
					for (Item item : order.getOrderedItems()) {
						if (item.getStatus().equals(ItemStatus.RETURN_PICKUP_REQUESTED.ordinal())) {
							item.setStatus(ItemStatus.REFUND_INITIATED.ordinal());
							itemService.addProductStockBackOnRemoveItem(item);
						}
					}
				}
			}

			Delivery delivery = order.getDelivery();
			delivery.setReturnCourierServiceName(dto.getReturnCourierServiceName());
			delivery.setReturnTrackingId(dto.getReturnTrackingId());
			delivery.setReturnShippingCharge(dto.getReturnShippingCharge());

			order.setIsReturnPickupArranged(false);
			order.setIsReturnedItemReceived(true);

			save(order);
		}
	}

	@Override
	public void confirmCODOrder(User user, String orderId) {
		Order order = findOneWithItems(orderId, user);
		if (order == null) {
			throw new RuntimeException("No order id while confirming COD order for orderId " + orderId);
		}
		order.setStatus(OrderStatus.OPEN.ordinal());
		save(order);
		cartService.emptyCart(user);
		messageService.sendConfirmCODOrderMessage(user, orderId, order);
	}

	@Override
	public PrepareOrderResultDto prepareOrder(Integer paymentMode, User user) {

		PrepareOrderResultDto result = new PrepareOrderResultDto();
		try {

			Set<Cart> carts = cartService.findByUserWithStockAndProduct(user);

			if (CollectionUtils.isEmpty(carts)) {
				cartService.emptyCart(user);
				return null;
			}

			result = buildOrder(carts, user, paymentMode);

			if (result.getOrder() == null) {
				reportProductUnavailability(result);
			}

		} catch (Exception e) {
			LOGGER.error("Error while prepare order", e);
			reportBuildOrderFailure(result, e);
		}

		return result;
	}

	private void reportProductUnavailability(PrepareOrderResultDto result) {
		messageService.sendEmailAndSmsToAdmin(
				"It seems not all products has the required stock to continue. Please re-verify the products with the updated stock and place the order once again.",
				"Order is null");
		result.setMessage(
				"It seems not all products has the required stock to continue. Please re-verify the products with the updated stock and place the order once again.");
		result.setReason(PlaceOrderFailReason.PRODUCT_COUNT_MISMATCH.name());
	}

	private void reportBuildOrderFailure(PrepareOrderResultDto result, Exception e) {
		messageService.sendEmailAndSmsToAdmin("Order is null in the exception block in order service impl",
				"Exception occurred while building order " + Util.retrieveStackTraceFromException(e));
		LOGGER.error("Unable to build order ", e);
		result.setMessage(
				"We're facing a technical difficulty while processing your order. Please try again later or contact customer care.");
		result.setReason(PlaceOrderFailReason.TECHNICAL_FAILURE.name());
	}

	@Override
	public void confirmNONCODOrderAfterPaymentIsMade(User user, String orderId, HttpServletRequest request) {
		Order order = findOneWithItems(orderId, user);
		if (order == null || !order.getStatus().equals(OrderStatus.NON_COD_PAYMENT_NOT_DONE.ordinal())) {
			throw new RuntimeException(
					"While confirming the order after payment for non-COD order.. order is null or status is not correct for orderId "
							+ orderId + " and status is " + order);
		}

		order.setStatus(OrderStatus.OPEN.ordinal());

		save(order);
		cartService.emptyCart(user);
		messageService.sendNONCODConfirmOrderMessage(user, orderId, order);
		Util.backupDatabase(request);
	}

	@Override
	public List<SoldoutProductDto> findExpiredOrdersAndSoldProducts() {
		Set<Product> products = productService.findSoldProducts();

		@SuppressWarnings("unused") // TODO - in future try to delete the orders
		List<Order> expiredOrders = orderRepository.findExpiredOrders(Util.getExpiredOrderStatus(),
				Util.getThirtyDaysBeforeDate(), Util.getSevenDaysBeforeDate());

		List<SoldoutProductDto> dtos = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(products)) {
			products.forEach(p -> {

				SoldoutProductDto so = new SoldoutProductDto();
				so.setProductId(p.getId());
				dtos.add(so);
			});
		}

		return dtos;
	}

}
