package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.service.VerificationService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class VerificationServiceImpl implements VerificationService {

	@Override
	public boolean verifyMobile(String mobile) {
		// TODO - implement mobile verification code
		return true;
	}

	@Override
	public int generateRandomNo() {
		return ThreadLocalRandom.current().nextInt(1000, 9999);
	}

	@Override
	public boolean sendSms(String mobile) {
		return true;
	}

	@Override
	public boolean sendEmail(String email) {
		// TODO implement email code
		return true;
	}

}
