package com.aarria.retail.web.dto.response;

public class WalletDetailsDto {

	private Long userId;
	private String mobileNo;
	private String depositedDate;
	private Double walletAmount;

	public WalletDetailsDto() {
		super();
	}

	public WalletDetailsDto(Long userId, String mobileNo, String depositedDate, Double walletAmount) {
		super();
		this.userId = userId;
		this.mobileNo = mobileNo;
		this.depositedDate = depositedDate;
		this.walletAmount = walletAmount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDepositedDate() {
		return depositedDate;
	}

	public void setDepositedDate(String depositedDate) {
		this.depositedDate = depositedDate;
	}

	public Double getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(Double walletAmount) {
		this.walletAmount = walletAmount;
	}

}
