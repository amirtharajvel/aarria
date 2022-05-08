package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.*;
import com.aarria.retail.core.service.CartService;
import com.aarria.retail.core.service.LogService;
import com.aarria.retail.core.service.OfferService;
import com.aarria.retail.core.service.ProductService;
import com.aarria.retail.core.util.Enum.Activity;
import com.aarria.retail.core.util.Enum.CheckoutType;
import com.aarria.retail.persistence.repository.CartRepository;
import com.aarria.retail.web.dto.request.AddToCartItemsDto;
import com.aarria.retail.web.dto.request.CheckoutCartDto;
import com.aarria.retail.web.dto.response.ApplyOfferHelperDto;
import com.aarria.retail.web.dto.response.CheckoutResponseDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private LogService logService;

    @Autowired
    private OfferService offerService;

    @Override
    public List<Cart> getCartByUser(User user, String checkoutType) {
        return cartRepository.findByUserCheckoutType(user, checkoutType);
    }

    @Override
    public Set<Cart> findByUserWithStockAndProduct(User user) {
        return cartRepository.findByUserWithStockAndProduct(user);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCartByUserAndPid(User user, List<String> pids) {
        return cartRepository.findByUserAndPid(user, pids);
    }

    @Override
    public void saveItemsToCart() {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveBuyNowCart(User user, Cart currentBuyNowCart) {

        mergeDuplicates(user);

        List<Cart> old = getCartByUser(user, CheckoutType.BUYNOW.name());
        if (old != null && !old.isEmpty()) {
            for (Cart cart : old) {
                cart.setCheckoutType(CheckoutType.ADDTOCART.name());
            }

            cartRepository.saveAll(old);
        }

        cartRepository.save(currentBuyNowCart);
    }

    @Override
    public void flushLocalStorage(User user, List<AddToCartItemsDto> dtos) {

        mergeDuplicates(user);

        List<String> pids = new ArrayList<>();

        if (dtos == null) {
            return;
        }

        for (AddToCartItemsDto dto : dtos) {
            pids.add(dto.getPid());
        }

        List<Cart> existingCarts = null;
        if (pids != null && !pids.isEmpty()) {
            existingCarts = getCartByUserAndPid(user, pids);
        }

        // this method can be improved!!
        for (AddToCartItemsDto dto : dtos) {

            boolean isSameProduct = false;

            if (existingCarts != null && !existingCarts.isEmpty()) {
                for (Cart cart : existingCarts) {

                    if (cart.getProduct() != null && cart.getProduct().getPid() != null) {
                        if (isSameProduct(dto.getPid(), dto.getSize(), cart)) {

                            int stock = getStock(dto.getSize(), cart);

                            Integer totalQuantity = dto.getQuantity() + cart.getQuantity();

                            Integer q = cart.getQuantity() + dto.getQuantity();

                            if (totalQuantity > stock) {
                                q = stock;
                            }

                            cart.setQuantity(q);
                            cart.setCheckoutType(CheckoutType.ADDTOCART.name());

                            Double newPrice = cart.getProduct().getPrice() * q;
                            cart.setPrice(newPrice);

                            this.saveCart(cart);

                            isSameProduct = true;

                            break;
                        }
                    }
                }
            }

            if (isSameProduct) {
                continue;
            }

            Product product = productService.findByPidWithStock(dto.getPid());

            // if old products found in browser, product will not be found here
            if (product == null) {
                continue;
            }

            Set<ProductStock> productStocks = product.getProductStock();
            Set<String> availableStockSizes = new HashSet<>();
            if (CollectionUtils.isNotEmpty(productStocks)) {
                for (ProductStock productStock : productStocks) {
                    availableStockSizes.add(productStock.getSize());
                }
            }

            if (!availableStockSizes.contains(dto.getSize())) {
                removeFromCart(user, dto.getPid(), dto.getSize());
            }

            Cart fresh = dto.getCart(user, product, CheckoutType.ADDTOCART.name());

            this.saveCart(fresh);

            System.out.println("insert new entry into Cart " + fresh);

        }
    }

    private boolean isSameProduct(String pid, String size, Cart cart) {

        boolean isSamePid = cart.getProduct().getPid().equals(pid);
        boolean isSameSize = cart.getSize().equals(size);

        return isSamePid && isSameSize; // which means same
        // product as of now
    }

    public void mergeDuplicates(User user) {

        Set<Cart> s = findByUserWithStockAndProduct(user); // something going wrong here as it
        // often return same cart twice in
        // query as size 2
        List<Cart> carts = findDuplicates(s);

        if (carts != null && !carts.isEmpty()) {
            cartRepository.deleteAll(carts);
        }
    }

    public static List<Cart> findDuplicates(Set<Cart> s) {

        if (s == null) {
            return null;
        }

        final List<Cart> toReturn = new ArrayList<>();
        final Set<Cart> con = new HashSet<>();

        for (Cart c : s) {
            if (!con.add(c)) {
                toReturn.add(c);
            }
        }

        return toReturn;
    }

    @Override
    public boolean removeFromCart(User user, String pid, String size) {
        List<String> pids = new ArrayList<>();
        pids.add(pid);
        Set<Cart> cartsToBeRemoved = cartRepository.findByUserAndPidAndSize(user, pids, size);
        if (!CollectionUtils.isEmpty(cartsToBeRemoved)) {
            cartsToBeRemoved.forEach(c -> cartRepository.delete(c));
            return true;
        }

        return false;
    }

    @Override
    public boolean reduceOneQuantityFromCart(User user, String pid, String size) {
        List<String> pids = new ArrayList<>();
        pids.add(pid);
        Set<Cart> carts = cartRepository.findByUserAndPidAndSize(user, pids, size);
        if (!CollectionUtils.isEmpty(carts)) {
            carts.forEach(cart -> {
                if (isSameProduct(pid, size, cart)) {
                    Integer q = cart.getQuantity();
                    if (q > 1) {
                        cart.setQuantity(--q);
                        cart.setCheckoutType(CheckoutType.ADDTOCART.name());

                        Double newPrice = cart.getProduct().getPrice() * q;
                        cart.setPrice(newPrice);

                        this.saveCart(cart);
                    }
                }
            });
        }
        return false;
    }

    @Override
    public boolean increaseOneQuantityToCart(User user, String pid, String size) {
        List<String> pids = new ArrayList<>();
        pids.add(pid);
        Set<Cart> carts = cartRepository.findByUserAndPidAndSize(user, pids, size);
        if (!CollectionUtils.isEmpty(carts)) {
            carts.forEach(cart -> {
                if (isSameProduct(pid, size, cart)) {
                    Integer q = cart.getQuantity();
                    int stock = 0;
                    stock = getStock(size, cart);

                    if (stock <= q) {
                        q = stock;
                    } else {
                        q++;
                    }

                    cart.setQuantity(q);
                    cart.setCheckoutType(CheckoutType.ADDTOCART.name());

                    Double newPrice = cart.getProduct().getPrice() * q;
                    cart.setPrice(newPrice);

                    this.saveCart(cart);
                }
            });
        }
        return false;
    }

    private int getStock(String size, Cart cart) {
        Product product = cart.getProduct();
        if (product != null) {
            if (product.getProductStock() != null) {
                ProductStock ps = product.getProductStock().stream().filter(p -> p.getSize().equals(size)).findFirst()
                        .orElse(null);
                if (ps != null) {
                    return ps.getStock();
                }
            }
        }
        return 0;
    }

    @Override
    public CheckoutResponseDto prepareCart(User user, ModelAndView view, String couponCode) {

        CheckoutResponseDto dto = new CheckoutResponseDto();
        List<Cart> fromCartPage = new ArrayList<>(findByUserWithStockAndProduct(user));
        List<CheckoutCartDto> checkoutItems = new ArrayList<>();

        double total = 0d;
        int totalQuantity = 0;
        double savings = 0;

        boolean isValidCouponCode = fromCartPage.stream()
                .filter(cart -> cart != null && cart.getProduct() != null)
                .map(cart -> cart.getProduct().getOfferCode())
                .filter(code -> code != null).anyMatch(offerCode -> offerCode.equals(couponCode));

        if (fromCartPage != null && !fromCartPage.isEmpty()) {

            for (int i = 0; i < fromCartPage.size(); i++) {

                Cart cart = fromCartPage.get(i);

                CheckoutCartDto cartDto = new CheckoutCartDto(cart, cart.getProduct());
                saveCart(cart);

                int stock = 0;
                Product product = cart.getProduct();

                if (product != null) {
                    Set<ProductStock> productStocks = product.getProductStock();
                    if (CollectionUtils.isNotEmpty(productStocks)) {
                        for (ProductStock s : productStocks) {
                            if (StringUtils.isNotEmpty(cart.getSize()) && StringUtils.isNotEmpty(s.getSize())) {
                                if (cart.getSize().trim().equals(s.getSize().trim())) {
                                    stock = s.getStock();
                                    break;
                                }
                            }
                        }
                    }
                }

                if (stock == 0) {
                    String activity = Activity.SIZE_JUST_OLD_OUT.name() + " size = " + cart.getSize()
                            + " from checkout page while placing order from Cart";
                    Log moreOrderThanStockLog = new Log(user, activity, new Date());
                    logService.save(moreOrderThanStockLog);

                    removeCart(cart);

                    continue;
                }

                cartDto.setSellingPriceBeforeOffer(product.getPrice());
                double priceTobeAdded = isValidCouponCode ?
                        offerService.getPriceAfterCouponCodeApplied(product.getOfferCode(), product.getPrice()) : product.getPrice();

                cartDto.setAmountAfterOfferApplied(priceTobeAdded);
                cartDto.setPrice(priceTobeAdded);
                savings += product.getActualPrice() - priceTobeAdded;

                checkoutItems.add(cartDto);

                total += priceTobeAdded;

                totalQuantity += cart.getQuantity();

            }
        }

        view.addObject("totalBeforeOffer", total);
        view.addObject("total", offerService.availOfferForWholeOrder(total, totalQuantity));
        view.addObject("savings", savings);

        if (couponCode != null) {
            dto.setCouponCodeAppliedMessage(offerService.isValidCouponCode(isValidCouponCode));
        } else {
            dto.setCouponCodeAppliedMessage(null);
        }

        dto.setItems(checkoutItems);
        dto.setTotalQuantity(totalQuantity);

        return dto;
    }

    private double applyOffer(ApplyOfferHelperDto dto) {

        double totalAmountToBeDeducted = 0.0d;
        if (dto != null && dto.getOfferPrice() != null && dto.getActualCartQuantity() != null
                && dto.getOfferQuantity() != null && dto.getActualCartQuantity() >= dto.getOfferQuantity()) {

            System.out.println(dto);

            double totalPriceWithoutOffer = dto.getActualCartQuantity() * dto.getProductPrice();
            double reminder = dto.getActualCartQuantity() % dto.getOfferQuantity();

            double factor = dto.getActualCartQuantity() / dto.getOfferQuantity();
            double totalPriceWithOffer = (dto.getOfferPrice() * factor) + (reminder * dto.getProductPrice());

            totalAmountToBeDeducted = totalPriceWithoutOffer - totalPriceWithOffer;

            System.out.println(" totalPriceWithoutOffer = " + totalPriceWithoutOffer);
            System.out.println("reminder = " + reminder);
            System.out.println("factor = " + factor);
            System.out.println("totalPriceWithOffer = " + totalPriceWithOffer);
            System.out.println("total amount to be deducted = " + totalAmountToBeDeducted);
        }

        return totalAmountToBeDeducted;
    }

    private double applyOffer(Map<String, ApplyOfferHelperDto> offers, Cart cart, Product product,
                              double priceTobeAdded) {
        // offers
        ApplyOfferHelperDto offer = offers.get(product.getOfferCode());
        if (offer == null) {
            offers.put(product.getOfferCode(), new ApplyOfferHelperDto(product.getOfferPrice(), product.getPrice(),
                    product.getOfferQuantity(), cart.getQuantity()));
        } else {
            offer.setActualCartQuantity(offer.getActualCartQuantity() + cart.getQuantity());
            offers.put(product.getOfferCode(), offer);
        }

        double amountToBeDeducted = applyOffer(offers.get(product.getOfferCode()));

        priceTobeAdded = priceTobeAdded - amountToBeDeducted;
        return priceTobeAdded;
    }

    @Override
    public void removeCart(Cart c) {
        cartRepository.delete(c);
    }

    @Transactional
    @Override
    public String emptyCart(User user) {
        Set<Cart> carts = cartRepository.findByUser(user);
        if (CollectionUtils.isEmpty(carts)) {
            return "Cart is empty";
        } else {
            carts.forEach(c -> {
                cartRepository.delete(c);
            });
        }

        return null;
    }
}
