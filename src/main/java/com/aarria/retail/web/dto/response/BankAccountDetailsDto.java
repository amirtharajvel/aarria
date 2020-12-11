package com.aarria.retail.web.dto.response;

public class BankAccountDetailsDto {

	private static final String WITH_STAR = "*";

	private static final String CHARACTERS = "(?s).";

	private String accountNo;

	private String ifscCode;

	private Long identifier;

	public BankAccountDetailsDto(String accountNo, String ifscCode, Long identifier) {
		super();
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
		this.identifier = identifier;
		hideAccountNumber();
	}

	private void hideAccountNumber() {
		if (this.accountNo != null && this.accountNo.length() > 2) {
			String lastTwo = this.accountNo.substring(this.accountNo.length() - 2, this.accountNo.length());
			String asterisk = this.accountNo.replaceAll(CHARACTERS, WITH_STAR);
			this.setAccountNo(asterisk + lastTwo);
		}
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		return "accountNo=" + accountNo + ", ifscCode=" + ifscCode;
	}

}
