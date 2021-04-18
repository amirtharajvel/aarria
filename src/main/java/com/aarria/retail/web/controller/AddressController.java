package com.aarria.retail.web.controller;

import com.aarria.retail.core.domain.Address;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.AddressService;
import com.aarria.retail.core.service.ValidationService;
import com.aarria.retail.core.util.View;
import com.aarria.retail.web.dto.response.AddressDto;
import com.aarria.retail.web.dto.response.LatLongResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private ValidationService validationService;

	@RequestMapping(value = "/addAddress", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addAddress(HttpSession session, @ModelAttribute AddressDto dto) {

		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:" + View.CHECKOUT);

		User user = validationService.findUserFromSession(session);

		if (dto == null || user == null || dto.getAddress() == null || dto.getMobile() == null) {
			return view;
		}

		addressService.saveAddress(dto.getAddressInstance(user));

		return view;
	}

	@RequestMapping(value = "/updateAddress", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView updateAddress(HttpSession session, @ModelAttribute AddressDto dto) {
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:" + View.CHECKOUT);

		User user = validationService.findUserFromSession(session);

		if (dto == null || user == null || dto.getAddress() == null || dto.getMobile() == null) {
			return view;
		}

		if (dto.getId() != null) {
			Address address = addressService.findOne(dto.getId());

			if (address == null) {
				if (session != null) {
					session.invalidate();
				}
				return view;
			}

			addressService.saveAddress(dto.getUpdateAddressInstance(address, user));

		}

		return view;
	}

	@RequestMapping(value = "/deliverHere", method = RequestMethod.GET)
	public ModelAndView deliverHere(HttpSession session, @RequestParam Long id) {

		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:" + View.CHECKOUT);

		User user = validationService.findUserFromSession(session);

		if (id == null || user == null) {
			return view;
		}

		Address address = addressService.deliverHere(user, id);

		if (address == null) {
			if (session != null) {
				session.invalidate();
			}
			return view;
		}

		return view;
	}

	@RequestMapping(value = "/deleteAddress/{id}", method = RequestMethod.GET)
	public ModelAndView deleteAddress(HttpSession session, @PathVariable("id") Long id) {

		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:" + View.CHECKOUT);

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			return view;
		}

		addressService.delete(id, user);

		return view;
	}

}
