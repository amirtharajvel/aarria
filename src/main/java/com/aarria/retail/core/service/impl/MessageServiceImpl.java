package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.OTP;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.MessageService;
import com.aarria.retail.core.service.OTPService;
import com.aarria.retail.core.util.AppProperties;
import com.aarria.retail.core.util.Constants;
import com.aarria.retail.core.util.Enum.OTPAction;
import com.aarria.retail.core.util.MailUtil;
import com.aarria.retail.core.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

	private static final int ONE = 1;

	private static final String SPACE = " ";

	private static final String YOUR_ORDER_FOR = " Your Order for ";

	private Logger LOGGER = LogManager.getLogger(MessageServiceImpl.class);

	private static final String COLON = ":";
	private static final String ORDER_CONFIRMED = "Order Confirmed";
	private static final String ORDER_CANCELLED = "Order Cancelled";

	@Autowired
	private AppProperties properties;

	@Autowired
	private OTPService otpService;

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public void sendSms(String message, String mobile) {
		this.sendSmsToUser(message, mobile);
	}

	@Override
	public void sendEmail(String message, String subject, String email) {

	}

	@Override
	public void sendEmailAndSmsToAdmin(String message, String subject) {
		sendEmailToAdmin(message, subject);
		sendSmsToAdmin(message);
	}

	@Override
	public void sendEmailToAdmin(String message, String subject) {
		try {
			
			MailUtil.sendEmail("ordersatfallsbuy@gmail.com", subject, message);
			
		} catch (Exception e) {
			LOGGER.error("Error occurred in sendEmailToAdmin " + e);
		}
	}

	@Override
	public void sendEmailToAdmin(String email, String message, String subject) {
		MailUtil.sendEmail(email, subject, message);
	}

	@Override
	public void sendSmsToAdmin(String message) {

		if (properties.isTurnOffSms) {
			return;
		}

		new Thread(() -> {
			String mobile = "9901411006";
			final String uri = getPromotionalSMSURI(mobile, message);
			String result = restTemplate.getForObject(uri, String.class);
			LOGGER.info("Sms sent to admin mobile " + mobile + " result is " + result);
		}).start();
	}

	private void sendSmsToUser(String message, String mobile) {

		if (properties.isTurnOffSms) {
			return;
		}

		new Thread(() -> {
			final String uri = getPromotionalSMSURI(mobile, message);
			String result = restTemplate.getForObject(uri, String.class);
			LOGGER.info("Sms sent to mobile " + mobile + " result is " + result);
		}).start();
		sendEmailToAdmin("amirtharaj@live.com", message, "OTP sent to mobile " + mobile);
	}

	@Override
	public void sendOTPSms(String mobile, String code, OTPAction action) {

		if (properties.isTurnOffSms) {
			return;
		}

		if (!isValidOTPAttempt(mobile, action)) {
			return;
		}

		final String message = getSMSOTPFormat(code);
		final String uri = getOTPSMSURI(mobile, code);

		new Thread(() -> {
			String result = restTemplate.getForObject(uri, String.class);
			LOGGER.info("OTP " + code + " sent to " + mobile + " result is " + result + " for action " + action.name());
		}).start();
		sendEmailToAdmin("amirtharaj@live.com", message,
				"OTP sent to mobile " + mobile + " for action " + action.name());

	}

	private boolean isValidOTPAttempt(String mobile, OTPAction action) {

		String last10Digits = Util.getLast10Digits(mobile);
		OTP otp = otpService.findByMobile(last10Digits);

		boolean isMoreThan24Hours = otp == null ? false : Util.isMoreThan24Hours(otp.getDate());
		if (otp == null || isMoreThan24Hours) {
			otpService.save(new OTP(ONE, last10Digits, action.name(), Util.getIndiaTimeNow()));
			LOGGER.info("in sendOTPSms otp is " + otp + " isMoreThan24Hours is " + isMoreThan24Hours);
		} else {

			int count = isMoreThan24Hours ? 0 : otp.getCount();

			if (count > 5) {
				LOGGER.info(
						"ALERT!!!!! - Decided not to send OTP - " + otp + " isMoreThan24Hours is " + isMoreThan24Hours);
				return false;
			}

			if (isMoreThan24Hours) {
				otp.setDate(Util.getIndiaTimeNow());
			}

			otp.setCount(++count);
			otp.setActivity(action.name());
			otpService.save(otp);

		}

		return true;
	}

	private String getPromotionalSMSURI(String mobile, final String message) {
		final String uri = properties.getSmsGatwayUriPromotional() + mobile + "&message=" + message;
		return uri;
	}

	private String getOTPSMSURI(String mobile, final String code) {
		final String uri = properties.getSmsGatwayUriOTP() + mobile + "&otp=" + code;
		return uri;
	}

	private String getSMSOTPFormat(String code) {

		String date = new SimpleDateFormat(Constants.DATE_WITH_TIME).format(new Date());

		final String message = "Your One Time Password is " + code + " for www.aarria.com at " + date
				+ " - Happy Quality Shopping!";
		return message;
	}

	// order related methods
	@Override
	public void sendConfirmCODOrderMessage(User user, String orderId, Order order) {

		String message = ORDER_CONFIRMED + COLON + YOUR_ORDER_FOR + Util.messageTemplate(order, Constants.CONFIRMED);

		sendEmailAndSmsToAdmin(message, ORDER_CONFIRMED + COLON + SPACE + "COD - " + orderId);
		sendSms(message, user.getMobile());
	}

	@Override
	public void sendNONCODConfirmOrderMessage(User user, String orderId, Order order) {
		String message = ORDER_CONFIRMED + COLON + YOUR_ORDER_FOR + Util.messageTemplate(order, Constants.CONFIRMED);

		sendEmailAndSmsToAdmin(message, ORDER_CONFIRMED + COLON + SPACE + "NON - COD " + orderId);
		sendSms(message, user.getMobile());
	}

	@Override
	public void sendCancelledOrderMessage(Order order, User user) {

		String message = ORDER_CANCELLED + COLON + YOUR_ORDER_FOR + Util.messageTemplate(order, Constants.CANCELLED);

		sendEmailToAdmin(message, ORDER_CANCELLED + COLON + SPACE + order.getOrderId());
		sendSms(message, user.getMobile());
	}

	@Override
	public void sendMoneyDepositedToWalletMessage(User user, Double amount, Order order, String action) {
		String message = "Rs." + amount + " is " + action + " your Wallet against order " + order.getOrderId();
		sendSms(message, user.getMobile());
		sendEmailAndSmsToAdmin(message, "Rs. " + amount + " is " + action + " Wallet for user " + user.getId());
	}

	@Override
	public void sendMoneyDepositedToBankMessage(User user, Double amount, Order order) {

		String message = "Rs." + amount + " is refunded to your Bank account against order " + order.getOrderId();
		sendSms(message, user.getMobile());
		sendEmailAndSmsToAdmin(message, "Rs. " + amount + " is deposited to Bank Account for user " + user.getId());
	}

	@Override
	public void sendReturnPickupMessage(Order order, User user) {
		sendEmailToAdmin("Pick up arranged for order " + order.getOrderId(), "Pickup arranged");
		String dateAndTime = " on " + order.getPickupDateAndTime() + " at " + order.getReturnPickupAddress();
		sendSms("Pick up arranged for order " + order.getOrderId() + " for the amount "
				+ Util.getTotalOrderAmount(order) + dateAndTime + ". Please keep the package ready.", user.getMobile());
	}

	@Override
	public void sendReturnOrderPlacedMessage(User user, Order order) {
		sendEmailAndSmsToAdmin("Return Order Placed: Order for " + Util.messageTemplate(order, Constants.RETURNED),
				"Return order placed");
		sendSms("Return order request is placed for the order " + order.getOrderId() + " for the amount "
				+ Util.getTotalOrderAmount(order)
				+ ". We're checking for the pick up courier service. We'll update you the status shortly.",
				user.getMobile());

	}
}
