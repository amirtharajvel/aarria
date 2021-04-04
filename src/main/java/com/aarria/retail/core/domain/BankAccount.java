package com.aarria.retail.core.domain;

import javax.persistence.*;

@Entity
@Table(name = "bank_account")
@TableGenerator(name = "bank_account_generator", initialValue = 0, allocationSize = 1)
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "bank_account_generator")
	private Long id;

	@Column(name = "accountNo")
	private String accountNo;

	@Column(name = "ifscCode")
	private String ifscCode;

	@ManyToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
