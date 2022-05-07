package com.aarria.retail.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aarria.retail.core.util.AppProperties;
import com.aarria.retail.web.dto.request.LoginDto;
import com.aarria.retail.web.dto.request.RegisterInputDto;

import static com.aarria.retail.core.util.View.*;

@Controller
public class HomeController {

	Logger LOGGER = LogManager.getLogger(HomeController.class);

	@Autowired
	private AppProperties properties;

	@GetMapping(value = "/")
	public ModelAndView home() {
				
		ModelAndView modelAndView = new ModelAndView(HOME);
		modelAndView.addObject("cdnResources", properties.cdnResources);
		return modelAndView;
	}

	@RequestMapping(value = "/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView(REGISTER);
		modelAndView.getModelMap().put("form", new RegisterInputDto());
		return modelAndView;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView(LOGIN);
		modelAndView.getModelMap().put("form", new LoginDto());
		return modelAndView;
	}

//	@RequestMapping(value = "/error")
//	public ModelAndView error() {
//		ModelAndView modelAndView = new ModelAndView(ERROR);
//		return modelAndView;
//	}

	@RequestMapping(value = "/getOrders")
	public ModelAndView myOrders() {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + MY_ORDERS);
		return modelAndView;
	}

	@RequestMapping(value = "/cart")
	public ModelAndView cart() {
		ModelAndView modelAndView = new ModelAndView(CART);
		return modelAndView;
	}

	@RequestMapping(value = "/aboutus")
	public ModelAndView aboutus() {
		ModelAndView modelAndView = new ModelAndView(ABOUT_US);
		return modelAndView;
	}

	@RequestMapping(value = "/privacypolicy")
	public ModelAndView privacypolicy() {
		ModelAndView modelAndView = new ModelAndView(PRIVACY_POLICY);
		return modelAndView;
	}

	@RequestMapping(value = "/cookiespolicy")
	public ModelAndView cookiespolicy() {
		ModelAndView modelAndView = new ModelAndView(COOKIE_POLICY);
		return modelAndView;
	}

	@RequestMapping(value = "/termsofservice")
	public ModelAndView termsofservice() {
		ModelAndView modelAndView = new ModelAndView(TERMS_OF_SERVICE);
		return modelAndView;
	}

	@RequestMapping(value = "/sizechart")
	public ModelAndView sizechart() {
		ModelAndView modelAndView = new ModelAndView(SIZE_CHART);
		return modelAndView;
	}

	@RequestMapping(value = "/pincodeupload")
	public ModelAndView pincodeupload() {
		ModelAndView modelAndView = new ModelAndView(PINCODE_UPLOAD);
		return modelAndView;
	}

	@RequestMapping(value = "/forgotpassword")
	public ModelAndView forgotPassword() {
		ModelAndView modelAndView = new ModelAndView(FORGOT_PASSWORD);
		return modelAndView;
	}

	@RequestMapping(value = "/changepassword")
	public ModelAndView changePassword() {
		ModelAndView modelAndView = new ModelAndView(CHANGE_PASSWORD);
		return modelAndView;
	}

	@RequestMapping(value = "/returnpolicy")
	public ModelAndView returnPolicy() {
		ModelAndView modelAndView = new ModelAndView(RETURN_POLICY);
		return modelAndView;
	}

	@RequestMapping(value = "/store")
	public ModelAndView store() {
		ModelAndView modelAndView = new ModelAndView(STORE);
		return modelAndView;
	}

	@RequestMapping(value = "/post")
	public ModelAndView post() {
		ModelAndView modelAndView = new ModelAndView(POST);
		return modelAndView;
	}
	
	@RequestMapping(value = "/here")
	public ModelAndView here() {
		ModelAndView modelAndView = new ModelAndView("here");
		return modelAndView;
	}

	// It removes session attribute
	@RequestMapping(value = "/removeAttribute", method = RequestMethod.GET)
	public void removeSessionAttribute(HttpSession session, @RequestParam("attribute") String attribute) {

		if (session != null) {
			if (attribute != null) {
				session.removeAttribute(attribute);
			}
		}
	}
}
