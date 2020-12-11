package com.aarria.retail.core.service;

public interface VerificationService {

	public boolean verifyMobile(String mobile);

	public int generateRandomNo();

	public boolean sendSms(String mobile);

	public boolean sendEmail(String email);

}
