package com.aarria.retail.web.dto.request;

public class AccountDto {

	private String email;

	private String mobile;

	private String username;

	private String name;

	private String walletAmount = "0";

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(String walletAmount) {
		this.walletAmount = walletAmount;
	}

	@Override
	public String toString() {
		return "AccountDto [email=" + email + ", mobile=" + mobile + ", username=" + username + ", name=" + name
				+ ", walletAmount=" + walletAmount + "]";
	}

}
