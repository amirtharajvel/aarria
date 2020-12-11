package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.util.Enum.OTPAction;

public interface MessageService {

	void sendSms(String message, String mobile);

	void sendEmail(String message, String subject, String email);

	void sendEmailAndSmsToAdmin(String message, String subject);

	void sendOTPSms(String mobile, String code, OTPAction action);

	void sendEmailToAdmin(String message, String subject);

	void sendEmailToAdmin(String email, String message, String subject);

	void sendSmsToAdmin(String message);

	// order messages
	void sendConfirmCODOrderMessage(User user, String orderId, Order order);

	void sendCancelledOrderMessage(Order order, User user);

	void sendNONCODConfirmOrderMessage(User user, String orderId, Order order);

	void sendMoneyDepositedToBankMessage(User user, Double amount, Order order);

	void sendReturnPickupMessage(Order order, User user);

	void sendReturnOrderPlacedMessage(User user, Order order);

	void sendMoneyDepositedToWalletMessage(User user, Double amount, Order order, String action);
}
