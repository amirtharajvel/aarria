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
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

    private static final int ONE = 1;

    private Logger LOGGER = LogManager.getLogger(MessageServiceImpl.class);

    private WebClient client = WebClient.create();

    @Autowired
    private AppProperties properties;

    @Autowired
    private OTPService otpService;

    @Override
    public void sendSms(Order message, String mobile, boolean isOtp) {
        this.sendSmsToUser(message, mobile, isOtp);
    }

    @Override
    public void sendEmail(String message, String subject, String email) {

    }

    @Override
    public void sendEmailAndSmsToAdmin(Order order, String subject) {
        sendEmailToAdmin(order, subject);
        sendSmsToAdmin(order, false);
    }

    @Override
    public void sendEmailToAdmin(Order order, String subject) {
        try {

            //TODO
            MailUtil.sendEmail("ordersatfallsbuy@gmail.com", subject, "order");

        } catch (Exception e) {
            LOGGER.error("Error occurred in sendEmailToAdmin " + e);
        }
    }

    @Override
    public void sendEmailToAdmin(String email, String message, String subject) {
        MailUtil.sendEmail(email, subject, message);
    }

    @Override
    public void sendSmsToAdmin(Order order, boolean isOtp) {

        if (properties.isTurnOffSms) {
            return;
        }

        new Thread(() -> {
            String mobile = "9901411006";
            String result = sendSmsUsingFlow(createSmsRequest(order, mobile, isOtp));
            LOGGER.info("Sms sent to admin mobile " + mobile + " result is " + result);
        }).start();
    }

    private Request createSmsRequest(Order order, String mobile, boolean isOtp) {

        String orderId = order != null ? order.getOrderId() : "Empty Order Id";
        String amount = order.getTotalOrderAmount() != null ? String.valueOf(order.getTotalOrderAmount()) : "-6";

        return new SmsRequest(Util.getFlowId(order, isOtp), mobile, Util.getOrderedProductName(order), orderId, amount);
    }

    private Request createOtpSmsRequest(String mobile, String otp) {
        return new OtpRequest(Util.getFlowId(null, true), mobile, otp, Util.getIndiaTimeNow().toString());
    }

    private void sendSmsToUser(Order order, String mobile, boolean isOtp) {

        if (properties.isTurnOffSms) {
            return;
        }

        new Thread(() -> {
            String result = sendSmsUsingFlow(createSmsRequest(order, mobile, isOtp));
            LOGGER.info("Sms sent to mobile " + mobile + " result is " + result);
        }).start();
        sendEmailToAdmin("amirtharaj@live.com", "OTP sent to mobile " + mobile, "OTP sent to mobile " + mobile);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private class Request {

        private String flow_id;
        private String mobiles;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private class SmsRequest extends Request {

        private String name;

        @JsonProperty("order-id")
        private String orderId;
        private String amount;

        public SmsRequest(String flowId, String mobile, String orderedProductName, String orderId, String amount) {
            super(flowId, mobile);

            this.name = orderedProductName;
            this.orderId = orderId;
            this.amount = amount;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private class OtpRequest extends Request {

        private String otp;
        private String time;

        public OtpRequest(String flowId, String mobile, String otp, String time) {
            super(flowId, mobile);

            this.otp = otp;
            this.time = time;
        }
    }

    private String sendSmsUsingFlow(Request request) {
        String response = null;

        try {

            //Request request = new Request("60efa52810c60c16bb0a5575",mobile, "Sare", "Order-id","amount");
            response = client.post()
                    .uri(new URI("https://api.msg91.com/api/v5/flow/"))
                    .header("authkey", "147259AGbe4IxaKNP058dfaa20")
                    .header("content-type", "application/JSON")
                    //.contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(request))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            System.out.println("response is " + response);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Unable to send flow SMS ", e);
            response = e.getMessage();
        }

        return response;
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
        final String uri = getPromotionalSMSURI(mobile, code);

        System.out.println("Message is " + message);
        new Thread(() -> {
            String result = sendSmsUsingFlow(createOtpSmsRequest(mobile, code));
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

    private String getSMSOTPFormat(String code) {

        String date = new SimpleDateFormat(Constants.DATE_WITH_TIME).format(new Date());

        final String message = "Your One Time Password is " + code + " for www.aarria.com at " + date
                + " - Happy Quality Shopping!";
        return message;
    }

    // order related methods
    @Override
    public void sendConfirmCODOrderMessage(User user, String orderId, Order order) {
        sendSms(order, user.getMobile(), false);
    }

    @Override
    public void sendNONCODConfirmOrderMessage(User user, String orderId, Order order) {
        sendSms(order, user.getMobile(), false);
    }

    @Override
    public void sendCancelledOrderMessage(Order order, User user) {
        sendSms(order, user.getMobile(), false);
    }

    @Override
    public void sendMoneyDepositedToWalletMessage(User user, Double amount, Order order, String action) {
        //TODO
    }

    @Override
    public void sendMoneyDepositedToBankMessage(User user, Double amount, Order order) {
        //TODO
    }

    @Override
    public void sendReturnPickupMessage(Order order, User user) {
        sendEmailToAdmin(order, "Pick up arranged for order " + order.getOrderId() + "Pickup arranged");
        String dateAndTime = " on " + order.getPickupDateAndTime() + " at " + order.getReturnPickupAddress();
        //TODO
        //        sendSms("Pick up arranged for order " + order.getOrderId() + " for the amount "
        //                + Util.getTotalOrderAmount(order) + dateAndTime + ". Please keep the package ready.", user.getMobile());
    }

    @Override
    public void sendReturnOrderPlacedMessage(User user, Order order) {
        //        sendEmailAndSmsToAdmin("Return Order Placed: Order for " + Util.messageTemplate(order, Constants.RETURNED),
        //                "Return order placed");
        sendSms(order,
                user.getMobile(), false);

    }
}
