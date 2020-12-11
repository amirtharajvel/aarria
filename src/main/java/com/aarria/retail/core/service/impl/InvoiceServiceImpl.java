package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Item;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.InvoiceService;
import com.aarria.retail.core.service.MessageService;
import com.aarria.retail.core.service.OrderService;
import com.aarria.retail.core.util.Enum.ItemStatus;
import com.aarria.retail.web.dto.response.AddressDto;
import com.aarria.retail.web.dto.response.PrintInvoiceDto;
import com.aarria.retail.web.dto.response.ViewInvoiceDto;
import com.aarria.retail.web.dto.response.ViewInvoiceItemsDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private OrderService orderService;

	@Autowired
	private MessageService messageService;

	@Override
	public PrintInvoiceDto generateInvoiceForTransport(String orderId, User user) {

		Order orderForUser = orderService.findByOrderNo(orderId);

		user = orderForUser.getUser();

		Order order = orderService.findOneWithItems(orderId, user);

		PrintInvoiceDto dto = new PrintInvoiceDto();
		if (order != null) {
			if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
				AddressDto address = AddressDto.createDtoFromAddress(order.getDelivery().getBillingAddress());
				dto.setBillingAddress(address);
				dto.setShippingAddress(address);

				String invoiceDate = null;
				if (order.getOrderedDate() != null) {
					invoiceDate = new SimpleDateFormat("dd-MM-YYYY").format(order.getOrderedDate());
				}

				dto.setInvoiceDate(invoiceDate);
				dto.setReceiptVoucher(order.getId() + "");
				dto.setItems(createTransportInvoiceForItems(order, dto));
				dto.setInvoiceNo(order.getOrderId());
				dto.setIsCashOnDelivery(order.getIsCashOnDelivery());
			}

			return dto;
		}

		return null;
	}

	@Override
	public ViewInvoiceDto generateInvoiceForUser(String orderId, User user) {
		Order order = orderService.findOneWithItems(orderId, user);
		ViewInvoiceDto dto = new ViewInvoiceDto();
		if (order != null) {
			if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
				AddressDto address = AddressDto.createDtoFromAddress(order.getDelivery().getBillingAddress());
				dto.setBillingAddress(address);
				dto.setShippingAddress(address);

				String invoiceDate = null;
				if (order.getOrderedDate() != null) {
					invoiceDate = new SimpleDateFormat("dd-MM-YYYY").format(order.getOrderedDate());
				}

				dto.setInvoiceDate(invoiceDate);
				dto.setReceiptVoucher(order.getId() + "");
				dto.setItems(createInvoiceForItems(order, dto));
				dto.setInvoiceNo(order.getOrderId());
			}

			return dto;
		}

		return null;
	}

	private List<ViewInvoiceItemsDto> createTransportInvoiceForItems(Order order, PrintInvoiceDto dto) {
		List<ViewInvoiceItemsDto> invoiceItems = new ArrayList<>();
		int i = 0;
		double totalInvoiceAmount = 0d;
		double averageShippingCharge = getAverageShippingCharge(order);

		for (Item item : order.getOrderedItems()) {
			if (isValidItemToTotal(item)) {
				ViewInvoiceItemsDto itemDto = new ViewInvoiceItemsDto();
				itemDto.setDescriptionOfSupplies(item.getProduct().getName());
				itemDto.setDiscount(item.getDiscount());
				itemDto.setHanCode(item.getProduct().getIgstCode());
				itemDto.setIgstAmount(item.getIgstAmount());
				itemDto.setIgstRate(item.getIgstRate());
				itemDto.setNetAmount(item.getTotalSoldPrice());
				itemDto.setQuantity(item.getQuantity());
				itemDto.setSerialNo(++i);
				itemDto.setTaxableValue(null);
				itemDto.setTotalBasePrice(null);
				itemDto.setUnitPrice(item.getUnitSoldPrice());
				itemDto.setIgstRate(item.getIgstRate());

				invoiceItems.add(itemDto);

				calculateTaxableAmounts(itemDto, item, averageShippingCharge);

				totalInvoiceAmount += item.getTotalSoldPrice();
			}
		}

		dto.setTotalInvoiceAmount(totalInvoiceAmount);

		return invoiceItems;
	}

	private List<ViewInvoiceItemsDto> createInvoiceForItems(Order order, ViewInvoiceDto dto) {
		List<ViewInvoiceItemsDto> invoiceItems = new ArrayList<>();
		int i = 0;
		double totalInvoiceAmount = 0d;
		double averageShippingCharge = getAverageShippingCharge(order);

		for (Item item : order.getOrderedItems()) {
			if (isValidItemToTotal(item)) {
				ViewInvoiceItemsDto itemDto = new ViewInvoiceItemsDto();
				itemDto.setDescriptionOfSupplies(item.getProduct().getName());
				itemDto.setDiscount(item.getDiscount());
				itemDto.setHanCode(item.getProduct().getIgstCode());
				itemDto.setIgstAmount(item.getIgstAmount());
				itemDto.setIgstRate(item.getIgstRate());
				itemDto.setNetAmount(item.getTotalSoldPrice());
				itemDto.setQuantity(item.getQuantity());
				itemDto.setSerialNo(++i);
				itemDto.setTaxableValue(null);
				itemDto.setTotalBasePrice(null);
				itemDto.setUnitPrice(item.getUnitSoldPrice());
				itemDto.setIgstRate(item.getIgstRate());

				invoiceItems.add(itemDto);

				calculateTaxableAmounts(itemDto, item, averageShippingCharge);

				totalInvoiceAmount += item.getTotalSoldPrice();
			}
		}

		dto.setTotalInvoiceAmount(totalInvoiceAmount);

		return invoiceItems;
	}

	private Double getAverageShippingCharge(Order order) {
		Double shippingCharge = order.getDelivery().getShippingCharge();
		if (shippingCharge == null) {
			// messageService.sendEmailAndSmsToAdmin("Shipping charge is null for order id "
			// + order.getOrderId(),
			// "Shipping Charge is null");
			return 0d;
		}

		double averageShippingCharge = shippingCharge / order.getOrderedItems().size();
		return averageShippingCharge;
	}

	private void calculateTaxableAmounts(ViewInvoiceItemsDto itemDto, Item item, Double averageShippingCharge) {

		double factor = 1.05;
		Double totalBasePrice = formatTo2Digits(item.getTotalSoldPrice() / factor);

		Double igstAmount = formatTo2Digits(item.getTotalSoldPrice() - totalBasePrice);

		itemDto.setTotalBasePrice(totalBasePrice);
		itemDto.setIgstAmount(igstAmount);
		itemDto.setTaxableValue(totalBasePrice);

	}

	private Double formatTo2Digits(Double amount) {
		return Double.valueOf(new DecimalFormat("#.##").format(amount));
	}

	private boolean isValidItemToTotal(Item item) {
		if (item.getStatus().equals(ItemStatus.OPEN.ordinal())) {
			return true;
		}
		return false;
	}

}
