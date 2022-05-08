package com.aarria.retail.web.controller;

import com.aarria.retail.core.domain.Address;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.AddressService;
import com.aarria.retail.core.service.CartService;
import com.aarria.retail.core.service.OfferService;
import com.aarria.retail.core.service.UserService;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.core.util.View;
import com.aarria.retail.web.dto.request.AddToCartItemsDto;
import com.aarria.retail.web.dto.request.CheckoutCartDto;
import com.aarria.retail.web.dto.request.CheckoutDto;
import com.aarria.retail.web.dto.response.AddressDto;
import com.aarria.retail.web.dto.response.CheckoutResponseDto;
import com.google.gson.Gson;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class CheckoutController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OfferService offerService;

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView checkout(HttpSession session, CheckoutDto dto, @ModelAttribute("message") String message,
                                 @RequestParam("target") Optional<String> target) {

        String targetValue = null;

        if (target.isPresent()) {
            targetValue = target.get();
            session.setAttribute("target", targetValue);
        }

        List<AddToCartItemsDto> cartItems = dto.getCartItemObject(new Gson());

        ModelAndView view = new ModelAndView();
        view.setViewName(View.CHECKOUT);

        if (session.getAttribute("userId") == null) {
            view.addObject("isUserLoggedIn", "false");

            return view;
        }

        User user = null;

        Object newUserMobile = session.getAttribute("isNewUserCreated");
        if (newUserMobile != null) {
            user = userService.findUserByMobile(newUserMobile + "", true);
        } else {
            Object userId = session.getAttribute("userId");
            if (userId == null) {
                session.invalidate();
                view.setViewName("redirect:" + View.LOGIN);
                return view;
            }

            user = userService.findOne(Long.valueOf(userId.toString()));
        }

        List<AddressDto> addressDtos = null;

        List<Address> addresses = addressService.findByUser(user);
        if (user != null) {
            addressDtos = Util.prepareAddresses(addresses);

            if (CollectionUtils.isNotEmpty(cartItems)) {
                cartService.flushLocalStorage(user, cartItems);
            }
        }

        if (addressDtos == null || addressDtos.isEmpty()) {
            view.addObject("addresses", "nil");
        } else {
            view.addObject("addresses", addressDtos);
            view.addObject("deliverHere", Util.deliverHere(addresses));
        }

        String targetString = (String) session.getAttribute("target");
        if (targetString != null) {
            dto.setCouponCode(targetString);//TODO
            session.removeAttribute("target");
        }

        CheckoutResponseDto result = cartService.prepareCart(user, view, dto.getCouponCode());
        List<CheckoutCartDto> items = result.getItems();

        if (items.size() == 0) {
            view.addObject("items", "noitems");
        } else {
            view.addObject("items", items);
            view.addObject("totalQuantity", result.getTotalQuantity());
            view.addObject("couponCodeAppliedMessage", result.getCouponCodeAppliedMessage());
            view.addObject("couponCode", dto.getCouponCode());
        }

        if (message != null && !message.trim().equals("")) {
            view.addObject("message", message);
        }
        view.addObject("isUserLoggedIn", "true");

        return view;
    }

}
