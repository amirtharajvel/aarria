package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.OTP;
import com.aarria.retail.core.service.OTPService;
import com.aarria.retail.persistence.repository.OTPRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OTPServiceImpl implements OTPService {

	private static Logger LOGGER = LogManager.getLogger(OTPServiceImpl.class);
	
	@Autowired
	private OTPRepository repository;

	@Override
	public void save(OTP otp) {
		repository.save(otp);
	}

	@Override
	public OTP findByMobile(String mobile) {
		
		LOGGER.info("Check otp send is less than 10 times for mobile "+ mobile );
		
		List<OTP> otps = repository.findOneByMobile(mobile);
		if (CollectionUtils.isNotEmpty(otps)) {
			return otps.get(0);
		}
		return null;
	}

}
