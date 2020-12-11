package com.aarria.retail.web.dto.request;

import com.aarria.retail.core.domain.BankAccount;
import com.aarria.retail.core.domain.User;

public class BankAccountDto {

	private String accountNo;

	private String ifscCode;

	private String fromPage;

	public BankAccountDto() {
		super();
	}

	public BankAccountDto(String accountNo, String ifscCode) {
		super();
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
	}

	public BankAccount createBankAccount(User user) {
		BankAccount account = new BankAccount();
		account.setAccountNo(accountNo);
		account.setIfscCode(ifscCode);
		account.setUser(user);
		return account;
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

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	@Override
	public String toString() {
		return "BankAccountDto [accountNo=" + accountNo + ", ifscCode=" + ifscCode + "]";
	}

}
