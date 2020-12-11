package com.aarria.retail.core.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wallet")
@TableGenerator(name = "wallet_generator", initialValue = 0, allocationSize = 1)
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "wallet_generator")
	private Long id;

	@Column
	private Double amount = 0d;

	@OneToOne(fetch = FetchType.EAGER)
	private User user;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
