package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.OTP;

public interface OTPService {

	void save(OTP otp);
	
	OTP findByMobile(String mobile);
	
}
