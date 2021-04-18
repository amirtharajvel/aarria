package com.aarria.retail.web.rest.controller;

import com.aarria.retail.core.domain.Log;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.*;
import com.aarria.retail.core.util.Constants;
import com.aarria.retail.core.util.Enum.OTPAction;
import com.aarria.retail.core.util.MailUtil;
import com.aarria.retail.core.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class VerificationController {

	private static Logger LOGGER = LogManager.getLogger(VerificationController.class);

	@Autowired
	private VerificationService verificationService;

	@Autowired
	private UserService userService;

	@Autowired
	private LogService logService;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private MessageService messageService;

	@RequestMapping("/sendEmail")
	public @ResponseBody String sendEmail(HttpSession session, @RequestParam("email") String email) {

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			return "unauthorized";
		}

		int code = verificationService.generateRandomNo();

		LOGGER.info("code " + code);

		session.setAttribute(email, code);

		if (verificationService.sendEmail(email)) {
			return "verificationcodesent";
		}

		return "true";

	}

	@RequestMapping("/sendSms")
	public @ResponseBody String sendSms(HttpSession session, @RequestParam("mobile") String mobile) {

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			return "unauthorized";
		}

		int code = verificationService.generateRandomNo();

		LOGGER.info("code " + code);

		session.setAttribute(mobile, code);

		verificationService.sendSms(mobile);

		return code + "";
	}

	@RequestMapping("/isMobileRegistered")
	public @ResponseBody String isMobileRegistered(HttpSession session, @RequestParam("mobile") String mobile) {

		User user = userService.findUserByMobile(mobile, Constants.VERIFIED);
		if (user != null) {
			return "registered";
		} else {
			return "notregistered";
		}
	}

	@RequestMapping("/verifyMobile")
	public @ResponseBody String verifyMobile(HttpSession session, @RequestParam("mobile") String mobile, String code) {

		String codeInApplication = String.valueOf(session.getAttribute(mobile));

		session.removeAttribute(mobile);
		
		if (code == null || codeInApplication == null) {
			if (session != null) {
				session.invalidate();
			}
			return "malfunctioned";
		}

		if (codeInApplication.toString().equals(code)) {

			User user = userService.findUserByMobile(mobile, Constants.NOT_VERIFIED);

			if (user == null) {
				return "usernotfound";
			}

			String oldMobile = user.getMobile();

			if (!oldMobile.equals(mobile)) {
				return "mobilemismatch";
			}

			user.setMobile(mobile);
			user.setMobileVerified(Constants.VERIFIED);

			userService.saveUser(user);

			Log databaseLog = new Log();
			databaseLog.setActivity("User updated mobile from " + oldMobile + " to " + mobile);
			databaseLog.setDate(new Date());
			databaseLog.setUser(user);

			logService.save(databaseLog);

			session.setAttribute("userId", user.getId());
			session.setAttribute("greeting", Util.withStarts(mobile));

			return "success";
		}

		return "failed";
	}

	@RequestMapping("/verifyMobileOnForgotPassword")
	public @ResponseBody String verifyMobileOnForgotPassword(HttpSession session, @RequestParam("mobile") String mobile,
			String code) {

		String codeInApplication = String.valueOf(session.getAttribute(mobile));

		session.removeAttribute(mobile);
		
		if (code == null || codeInApplication == null) {
			if (session != null) {
				session.invalidate();
			}
			return "malfunctioned";
		}

		if (codeInApplication.toString().equals(code)) {

			User user = userService.findUserByMobile(mobile, Constants.VERIFIED);

			if (user == null) {
				return "usernotfound";
			}

			String oldMobile = user.getMobile();

			if (!oldMobile.equals(mobile)) {
				return "mobilemismatch";
			}

			user.setMobile(mobile);
			user.setMobileVerified(Constants.VERIFIED);

			userService.saveUser(user);

			Log databaseLog = new Log();
			databaseLog.setActivity("User updated mobile from " + oldMobile + " to " + mobile);
			databaseLog.setDate(new Date());
			databaseLog.setUser(user);

			logService.save(databaseLog);

			session.setAttribute("userId", user.getId());
			session.setAttribute("greeting", Util.withStarts(mobile));

			return "success";
		}
		
		return "failed";
	}

	@RequestMapping("/verifyEmail")
	public @ResponseBody String verifyEmail(HttpSession session, @RequestParam("email") String email, String code) {

		if (session.getAttribute("userId") == null) {
			if (session != null) {
				session.invalidate();
			}
			return "unauthorized";
		}

		Integer sessionCode = (Integer) session.getAttribute(email);

		if (sessionCode == null) {
			if (session != null) {
				session.invalidate();
			}
			return "malfunctioned";
		}

		if (sessionCode.toString().equals(code)) {

			User user = userService.findOne(Long.parseLong(session.getAttribute("userId").toString()));

			if (user == null) {
				return "usernotfound";
			}

			String oldEmail = user.getEmail();

			user.setEmail(email);

			userService.saveUser(user);

			Log databaseLog = new Log();
			databaseLog.setActivity("User updated email from " + oldEmail + " to " + email);
			databaseLog.setDate(new Date());
			databaseLog.setUser(user);

			logService.save(databaseLog);
			session.setAttribute("greeting", Util.withStarts(user.getMobile()));

			return "success";
		}

		return "failed";
	}

	@RequestMapping(value = "/sendVerificationCodeForEmailOrMobile")
	public @ResponseBody String sendSmsOrEmail(HttpServletRequest request,
			@RequestParam("emailOrMobile") String emailOrMobile) {

		if (emailOrMobile == null) {
			return "Failure";
		}

		int code = verificationService.generateRandomNo();

		LOGGER.info("code " + code);

		HttpSession session = request.getSession();

		session.setAttribute(emailOrMobile, code);

		LOGGER.info("sendVerificationCodeForEmailOrMobile " + session.getAttribute(emailOrMobile));

		if (Util.isEmail(emailOrMobile)) {
			MailUtil.sendEmail(emailOrMobile, "Your OTP is " + code, "OTP");
		} else if (Util.isMobile(emailOrMobile)) {
			messageService.sendOTPSms(emailOrMobile, code + "", OTPAction.SEND_EMAIL_OR_MOBILE);
		}

		return "verificationcodesent";
	}

	@RequestMapping(value = "/verifySmsOrEmail")
	public @ResponseBody String verifySmsOrEmail(HttpServletRequest request,
			@RequestParam("emailOrMobile") String emailOrMobile, @RequestParam("code") String code) {

		HttpSession session = request.getSession();

		LOGGER.info("isSessionNew " + session.isNew());

		LOGGER.info("verifySmsOrEmail " + session.getAttribute(emailOrMobile));

		Integer sessionCode = (Integer) session.getAttribute(emailOrMobile);

		if (sessionCode == null) {
			if (session != null) {
				session.invalidate();
			}
			return "malfunctioned";
		}

		if (sessionCode.toString().equals(code)) {
			

			/*
			 * // uncomment if double confirmation needed if
			 * (userService.isUserPresent(emailOrMobile)) { return "true"; }
			 */

			return "success";
		}

		return "failure";
	}
}
