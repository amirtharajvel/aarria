package com.aarria.retail.core.domain;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@TableGenerator(name = "payment_generator", initialValue = 0, allocationSize = 1)
public class Payment {

	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "payment_generator")
	@Id
	private Long id;

	@OneToOne
	private User user;

	@Column
	private Double amountPaid;

	@Column
	private Integer paymentMode;

	public Payment() {
		super();
	}

	public Payment(Order order, User user, Double amountPaid, Integer paymentMode) {
		super();
		this.user = user;
		this.amountPaid = amountPaid;
		this.paymentMode = paymentMode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Integer getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

}
