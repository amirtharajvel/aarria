package com.aarria.retail.web.rest.controller;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.CartService;
import com.aarria.retail.core.service.ValidationService;
import com.aarria.retail.core.util.View;
import com.aarria.retail.web.dto.request.AddToCartItemsDto;
import com.aarria.retail.web.dto.request.IncreaseOneQuantityDto;
import com.aarria.retail.web.dto.request.ReduceOneQuantityDto;
import com.aarria.retail.web.dto.response.ViewCartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ValidationService validationService;

	@RequestMapping(value = "/addToCart", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object addToCart(@RequestBody List<AddToCartItemsDto> cartItems, HttpSession session) {

		System.out.println("add to cart items " + cartItems);

		if (cartItems == null) {
			return null;
		}

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			return "unauthorized";
		}

		cartService.flushLocalStorage(user, cartItems);

		return "authorized";
	}

	@RequestMapping(value = "/viewCart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ViewCartDto getCart(HttpSession session) {

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			return null;
		}

		cartService.flushLocalStorage(user, null);

		return new ViewCartDto(cartService.findByUserWithStockAndProduct(user));
	}

	@RequestMapping(value = "/removeFromCart/{pid}/size/{size}", method = RequestMethod.GET)
	public String removeFromCart(HttpSession session, @PathVariable("pid") String pid,
			@PathVariable("size") String size) {

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			return "unauthorized";
		}

		boolean isCartItemRemoved = cartService.removeFromCart(user, pid, size);

		if (isCartItemRemoved) {
			return "success";
		}

		return "false";
	}

	@RequestMapping(value = "/removeFromCartFromCheckout/{pid}/size/{size}", method = RequestMethod.GET)
	public ModelAndView removeFromCartFronCheckoutPage(HttpSession session, @PathVariable("pid") String pid,
			@PathVariable("size") String size) {

		ModelAndView view = new ModelAndView("redirect:" + View.CHECKOUT);

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			return view;
		}

		cartService.removeFromCart(user, pid, size);

		return view;
	}

	@RequestMapping(value = "/reduceOneQuantityFromCart/{pid}/size/{size}", method = RequestMethod.GET)
	public String reduceOneQuantityFromCart(HttpSession session, @PathVariable("pid") String pid,
			@PathVariable("size") String size) {

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			return "unauthorized";
		}

		boolean isCartItemRemoved = cartService.reduceOneQuantityFromCart(user, pid, size);

		if (isCartItemRemoved) {
			return "success";
		}

		return "false";
	}

	@RequestMapping(value = "/reduceOneQuantityFromCart", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView reduceOneQuantityFromCartOnCheckout(HttpSession session, ReduceOneQuantityDto dto) {

		ModelAndView view = new ModelAndView("redirect:" + View.CHECKOUT);

		User user = validationService.findUserFromSession(session);

		if (user == null || dto == null || dto.getPid() == null || dto.getSize() == null) {
			return view;
		}

		cartService.reduceOneQuantityFromCart(user, dto.getPid(), dto.getSize());

		return view;
	}

	@RequestMapping(value = "/increaseOneQuantityToCart", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView increaseOneQuantityToCartOnCheckout(HttpSession session, IncreaseOneQuantityDto dto) {

		ModelAndView view = new ModelAndView("redirect:" + View.CHECKOUT);

		User user = validationService.findUserFromSession(session);

		if (user == null || dto == null || dto.getPid() == null || dto.getSize() == null) {
			return view;
		}

		cartService.increaseOneQuantityToCart(user, dto.getPid(), dto.getSize());

		return view;
	}
}
