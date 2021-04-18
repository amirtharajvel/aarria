package com.aarria.retail.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.domain.Wallet;
import com.aarria.retail.core.service.BankAccountService;
import com.aarria.retail.core.service.CartService;
import com.aarria.retail.core.service.MessageService;
import com.aarria.retail.core.service.UserService;
import com.aarria.retail.core.service.VerificationService;
import com.aarria.retail.core.service.WalletService;
import com.aarria.retail.core.util.Constants;
import com.aarria.retail.core.util.Enum.OTPAction;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.core.util.View;
import com.aarria.retail.web.dto.request.AccountDto;
import com.aarria.retail.web.dto.request.AddToCartItemsDto;
import com.aarria.retail.web.dto.request.ChangePasswordDto;
import com.aarria.retail.web.dto.request.CreateNewUserOnCheckoutDto;
import com.aarria.retail.web.dto.request.LoginDto;
import com.aarria.retail.web.dto.request.OtpDto;
import com.aarria.retail.web.dto.request.RegisterInputDto;
import com.aarria.retail.web.dto.response.BankAccountDetailsDto;
import com.google.gson.Gson;

@Controller
public class LoginAndRegisterController {

	private static Logger LOGGER = LogManager.getLogger(LoginAndRegisterController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private VerificationService verificationService;

	@Autowired
	private CartService cartService;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView join(@ModelAttribute("form") @Valid RegisterInputDto dto, HttpSession session) {

		ModelAndView view = new ModelAndView();
		view.setViewName("register");

		if (userService.findUserByMobile(dto.getMobile(), Constants.VERIFIED) != null) {
			view.addObject("userExists", dto.getMobile());
			return view;
		}

		User user = userService.findUserByMobile(dto.getMobile(), Constants.NOT_VERIFIED);

		if (user == null) {
			user = userService.saveUser(dto.getUser());
		}

		if (user != null) {

			view.addObject("mobile", user.getMobile());
			int code = verificationService.generateRandomNo();

			messageService.sendOTPSms(user.getMobile(), code + "", OTPAction.REGISTER);

			LOGGER.info("OTP code while registering using register page " + code + " for mobile " + user.getMobile());

			session.setAttribute(dto.getMobile(), code);

			return view;

		}

		view.setViewName("redirect:/");

		return view;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("form") LoginDto dto, HttpSession session) {

		ModelAndView view = new ModelAndView();
		view.setViewName(View.LOGIN);

		if (!userService.isUserPresent(dto)) {
			view.addObject("userDoesNotExists", dto.getMobile());
			return view;
		}

		User user = userService.login(dto);

		if (user != null) {

			Object existingSessionUserID = session.getAttribute("userId");

			if (existingSessionUserID != null) {
				// session.removeAttribute("userId");
			}

			session.setAttribute("userId", user.getId());
			if (user.getEmail() != null) {
				session.setAttribute("name", user.getEmail().substring(0, 5));
			}

			String mobile = user.getMobile();
			if (mobile != null) {
				session.setAttribute("greeting", Util.withStarts(mobile));
			}

			if (user.getIsAdmin() != null && user.getIsAdmin()) {
				session.setAttribute("isAdmin", true);
			}

			view.setViewName("redirect:/");
			return view;

		} else {
			view.addObject("invalidPassword", dto.getMobile());
			return view;
		}
	}

	@RequestMapping(value = "/loginOnCheckout", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView checkoutLogin(HttpSession session, LoginDto dto) {

		ModelAndView view = new ModelAndView(View.CHECKOUT);

		if (dto == null || dto.getMobile() == null) {
			view.setViewName(View.REDIRECT + View.CHECKOUT);
			return view;
		}

		List<AddToCartItemsDto> cartItems = dto.getCartItemObject(new Gson());

		if (!userService.isUserPresent(dto)) {

			int code = verificationService.generateRandomNo();
			messageService.sendOTPSms(dto.getMobile(), code + "", OTPAction.LOGIN_ON_CHECKOUT);

			LOGGER.info("otp for " + dto.getMobile() + " is " + code);

			session.setAttribute(dto.getMobile(), code);

			view.addObject("isSendOTP", "true");
			view.addObject("otpMobile", dto.getMobile());

			return view;
		}

		if (dto.getPassword() == null) {
			view.addObject("mobile", dto.getMobile());
			view.addObject("isShowPassword", "true");
			return view;
		}

		User user = userService.login(dto);

		if (user != null) {

			session.setAttribute("userId", user.getId());
			if (user.getEmail() != null) {
				session.setAttribute("name", user.getEmail().substring(0, 5));
			}
			if (user.getIsAdmin() != null && user.getIsAdmin()) {
				session.setAttribute("isAdmin", true);
				// return view;
			}

			String mobile = user.getMobile();
			if (mobile != null) {
				session.setAttribute("greeting", Util.withStarts(mobile));
			}

			if (CollectionUtils.isNotEmpty(cartItems)) {
				cartService.flushLocalStorage(user, cartItems);
			}

			view.setViewName("redirect:/checkout");

			return view;

		} else {
			view.addObject("mobile", dto.getMobile());
			view.addObject("isIncorrectPassword", "true");
			return view;
		}

	}

	@RequestMapping(value = "/verifyMobileOnCheckout", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView verifyOtp(HttpSession session, OtpDto dto) {
		ModelAndView view = new ModelAndView();
		view.setViewName("checkout");

		if (dto == null || dto.getMobile() == null || dto.getOtp() == null) {
			view.setViewName(View.REDIRECT + View.CHECKOUT);
			return view;
		}

		view.addObject("invalidOtp", "true");
		view.addObject("enteredOTP", dto.getOtp());
		view.addObject("mobile", dto.getMobile());
		view.addObject("otpMobile", dto.getMobile());

		if (session == null || session.getAttribute(dto.getMobile()) == null) {
			return view;
		}

		String sessionCode = session.getAttribute(dto.getMobile()) + "";

		if (sessionCode == null || !sessionCode.equals(dto.getOtp())) {
			return view;
		}

		view.getModelMap().remove("invalidOtp");
		view.getModelMap().remove("isSendOTP");

		session.setAttribute(dto.getMobile(), "verified");

		view.addObject("isValidOTP", "true");
		return view;

	}

	@RequestMapping(value = "createNewUserOnCheckout", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView createNewUserOnCheckout(HttpSession session, CreateNewUserOnCheckoutDto dto) {

		ModelAndView view = new ModelAndView();
		view.setViewName(View.REDIRECT + View.CHECKOUT);

		if (dto == null || dto.getMobile() == null || dto.getPassword() == null) {
			return view;
		}

		List<AddToCartItemsDto> items = dto.getCartItemObject(new Gson());

		User user = userService.findUserByMobile(dto.getMobile(), false);

		if (user == null) {
			user = User.createUser(dto.getMobile(), null, dto.getPassword());
		} else {

			String mobileFromSession = (String) session.getAttribute(dto.getMobile());
			if (mobileFromSession != null && mobileFromSession.equals("verified")) {
				user.setMobileVerified(true);
			} else {
				return view;
			}
		}

		User created = userService.saveUser(user);

		if (CollectionUtils.isNotEmpty(items)) {
			cartService.flushLocalStorage(user, items);
		}

		session.setAttribute("userId", created.getId());

		session.setAttribute("isNewUserCreated", dto.getMobile());
		
		String mobile = created.getMobile();
		if (mobile != null) {
			session.setAttribute("greeting", Util.withStarts(mobile));
		}
		
		return view;
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

	@RequestMapping("/myaccount")
	public ModelAndView myaccount(HttpSession session) {

		ModelAndView view = new ModelAndView();

		if (session.getAttribute("userId") == null) {
			view.setViewName("redirect:" + View.LOGIN);
			return view;
		}

		Long userId = Long.parseLong(session.getAttribute("userId").toString());
		view.setViewName("account");

		User user = userService.findOne(userId);

		if (user == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		AccountDto dto = new AccountDto();

		Wallet wallet = walletService.findByUser(user);
		if (wallet != null && wallet.getAmount() != null) {
			int w = Integer.valueOf(wallet.getAmount().intValue());
			dto.setWalletAmount(w + "");
		}

		dto.setEmail(user.getEmail());
		dto.setMobile(user.getMobile());
		dto.setName(user.getName());

		List<BankAccountDetailsDto> bankAccounts = bankAccountService.getAllBankAccountsForUser(user);

		if (CollectionUtils.isNotEmpty(bankAccounts)) {
			view.addObject("bankAccounts", bankAccounts);
		}

		view.addObject("user", dto);

		return view;
	}

	@RequestMapping(value = "changePassword", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String changePassword(HttpSession session, @RequestBody ChangePasswordDto dto) {

		if (session.getAttribute("userId") == null) {
			if (session != null) {
				session.invalidate();
			}
			return "unauthorized";
		}

		Long userId = Long.parseLong(session.getAttribute("userId").toString());

		User user = userService.findOne(userId);

		if (user == null) {
			return "unauthorized";
		}

		if (!Util.decodePassword(dto.getOldPassword(), user.getPassword())) {
			return "incorrectoldpassword";
		}

		if (Util.decodePassword(dto.getNewPassword(), user.getPassword())) {
			return "samepassword";
		}

		user.setPassword(Util.encodePassword(dto.getNewPassword()));

		userService.saveUser(user);

		return "success";
	}

	@RequestMapping(value = "changeName", method = RequestMethod.GET)
	public @ResponseBody String changeName(HttpSession session, @RequestParam("name") String name) {

		if (session.getAttribute("userId") == null) {
			if (session != null) {
				session.invalidate();
			}
			return "unauthorized";
		}

		Long userId = Long.parseLong(session.getAttribute("userId").toString());

		User user = userService.findOne(userId);

		if (user == null) {
			return "unauthorized";
		}

		user.setName(name);

		userService.saveUser(user);

		return "success";
	}

	@RequestMapping(value = "/isUserExists", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String isUserExists(@RequestParam("emailOrMobile") String emailOrMobile) {

		if (userService.isUserPresent(emailOrMobile)) {
			return "true";
		}

		return "false";
	}

	@RequestMapping("/generateOTP")
	public ModelAndView verifyMobile(HttpSession session, @RequestParam("mobile") String mobile) {
		ModelAndView view = new ModelAndView();
		view.setViewName(View.FORGOT_PASSWORD);

		if (userService.findUserByMobile(mobile, Constants.VERIFIED) == null) {
			view.addObject("userDoesNotExists", mobile);
			return view;
		}

		view.addObject("mobile", mobile);
		Integer code = verificationService.generateRandomNo();
		messageService.sendOTPSms(mobile, code + "", OTPAction.FORGOT_PASSWORD);

		LOGGER.info("OTP code on forgot password from login page " + code + " for mobile " + mobile);

		session.setAttribute(mobile, code);

		return view;

	}

	@RequestMapping(value = "changePasswordOnForgotPassword", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String changePasswordOnForgotPassword(HttpSession session,
			@RequestParam("newPassword") String newPassword, String mobile) {

		if (session.getAttribute("userId") == null) {
			if (session != null) {
				session.invalidate();
			}
			return "unauthorized";
		}

		Long userId = Long.parseLong(session.getAttribute("userId").toString());

		User user = userService.findOne(userId);

		if (user == null) {
			return "unauthorized";
		}

		user.setPassword(Util.encodePassword(newPassword));

		session.setAttribute("greeting", Util.withStarts(user.getMobile()));

		userService.saveUser(user);

		return "success";
	}

	@RequestMapping(value = "/updateEmail", method = RequestMethod.GET)
	public ModelAndView updateEmail(HttpSession session, @RequestParam("email") String email) {

		ModelAndView view = new ModelAndView("redirect:/myaccount");

		if (session.getAttribute("userId") == null) {
			view.setViewName("redirect:/login");
			return view;
		}

		Long userId = Long.parseLong(session.getAttribute("userId").toString());

		User user = userService.findOne(userId);

		if (user == null) {
			view.setViewName("redirect:/login");
			return view;
		}

		user.setEmail(email);
		userService.saveUser(user);

		return view;
	}

}
