package com.aarria.retail.core.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@TableGenerator(name = "orders_generator", initialValue = 0, allocationSize = 1)
public class Order implements Serializable {

	public Order() {
		super();
	}

	public Order(Long id, String orderId, Integer status) {
		this.id = id;
		this.orderId = orderId;
		this.status = status;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "orders_generator")
	private Long id;

	@OneToOne
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	private List<Item> orderedItems = new ArrayList<>();

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Delivery delivery;

	@NotFound(action = NotFoundAction.EXCEPTION)
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Payment payment;

	@Column
	private String orderId;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderedDate;

	@Column
	private Integer status;

	@Column
	private Boolean isCashOnDelivery;

	@Column
	private Integer returnCashMethod;

	// Even for item cancellation refer this field for the bank
	@Column
	private Long selectedAccountForRefund;

	@Column
	private String returnPickupAddress;

	// including items, even for single item, the refund is captured here
	@Column
	private Double totalRefundedAmount;

	@Column
	private String refundReceiptNumber;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date refundedDate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnedDate;

	@Column
	private String pickupDateAndTime;

	@Column
	private Boolean isAnyOfTheItemIsReturned;

	@Column
	private Boolean isReturnedItemReceived;

	// other than the OrderStatus.DELIVERED enum here it is there to differentiate
	// between refunds bcz cancel and return refunds are same as of now
	@Column
	private Boolean isDelivered;

	// instead of Enum.java we are capturing it here.. just to avoid confusion
	@Column
	private Boolean isReturnPickupArranged;

	// Return, remove comments
	@Column
	private String comments;

	@Column
	private Double walletAmountUsed;

	@Column
	private Double totalOrderAmount;

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

	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Item> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(List<Item> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Boolean getIsCashOnDelivery() {
		return isCashOnDelivery;
	}

	public void setIsCashOnDelivery(Boolean isCashOnDelivery) {
		this.isCashOnDelivery = isCashOnDelivery;
	}

	public Integer getReturnCashMethod() {
		return returnCashMethod;
	}

	public void setReturnCashMethod(Integer returnCashMethod) {
		this.returnCashMethod = returnCashMethod;
	}

	public String getReturnPickupAddress() {
		return returnPickupAddress;
	}

	public void setReturnPickupAddress(String returnPickupAddress) {
		this.returnPickupAddress = returnPickupAddress;
	}

	public Long getSelectedAccountForRefund() {
		return selectedAccountForRefund;
	}

	public void setSelectedAccountForRefund(Long selectedAccountForRefund) {
		this.selectedAccountForRefund = selectedAccountForRefund;
	}

	public Double getTotalRefundedAmount() {
		return totalRefundedAmount;
	}

	public void setTotalRefundedAmount(Double totalRefundedAmount) {
		this.totalRefundedAmount = totalRefundedAmount;
	}

	public Date getRefundedDate() {
		return refundedDate;
	}

	public void setRefundedDate(Date refundedDate) {
		this.refundedDate = refundedDate;
	}

	public String getRefundReceiptNumber() {
		return refundReceiptNumber;
	}

	public void setRefundReceiptNumber(String refundReceiptNumber) {
		this.refundReceiptNumber = refundReceiptNumber;
	}

	public String getPickupDateAndTime() {
		return pickupDateAndTime;
	}

	public void setPickupDateAndTime(String pickupDateAndTime) {
		this.pickupDateAndTime = pickupDateAndTime;
	}

	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	public Boolean getIsAnyOfTheItemIsReturned() {
		return isAnyOfTheItemIsReturned;
	}

	public void setIsAnyOfTheItemIsReturned(Boolean isAnyOfTheItemIsReturned) {
		this.isAnyOfTheItemIsReturned = isAnyOfTheItemIsReturned;
	}

	public Boolean getIsReturnedItemReceived() {
		return isReturnedItemReceived;
	}

	public void setIsReturnedItemReceived(Boolean isReturnedItemReceived) {
		this.isReturnedItemReceived = isReturnedItemReceived;
	}

	public Boolean getIsDelivered() {
		return isDelivered;
	}

	public void setIsDelivered(Boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public Boolean getIsReturnPickupArranged() {
		return isReturnPickupArranged;
	}

	public void setIsReturnPickupArranged(Boolean isReturnPickupArranged) {
		this.isReturnPickupArranged = isReturnPickupArranged;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Double getWalletAmountUsed() {
		return walletAmountUsed;
	}

	public void setWalletAmountUsed(Double walletAmountUsed) {
		this.walletAmountUsed = walletAmountUsed;
	}

	public Double getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(Double totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderId=" + orderId + ", user=" + user + ", orderedDate=" + orderedDate
				+ ", status=" + status + "]";
	}

}
