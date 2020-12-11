package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Cart;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.web.dto.request.AddToCartItemsDto;
import com.aarria.retail.web.dto.response.CheckoutResponseDto;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

public interface CartService {

	public List<Cart> getCartByUser(User user, String checkoutType);

	public Cart saveCart(Cart cart);

	List<Cart> getCartByUserAndPid(User user, List<String> pids);

	public void saveItemsToCart();

	public void saveBuyNowCart(User user, Cart currentBuyNowCart);

	public void flushLocalStorage(User user, List<AddToCartItemsDto> dtos);

	public Set<Cart> findByUserWithStockAndProduct(User user);

	public boolean removeFromCart(User user, String pid, String size);

	public boolean reduceOneQuantityFromCart(User user, String pid, String size);

	void removeCart(Cart c);

	public boolean increaseOneQuantityToCart(User user, String pid, String size);

	public String emptyCart(User user);

	public CheckoutResponseDto prepareCart(User user, ModelAndView view, String couponCode);
}
