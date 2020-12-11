package com.aarria.retail.core.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery")
@TableGenerator(name = "delivery_generator", initialValue = 0, allocationSize = 1)
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "delivery_generator")
	private Long id;

	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(fetch = FetchType.EAGER)
	private Address billingAddress;

	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(fetch = FetchType.EAGER)
	private Address shippingAddress;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date shippingDate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date expectedDeliveryDate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderedDate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveredDate;

	@Column
	private String receivedPerson;

	@Column
	private Boolean isCommonAddress;

	@Column
	private String trackingName;

	@Column
	private String trackingId;

	@Column
	private String shipper;

	@Column
	private Double shippingCharge;

	@Column
	private String returnTrackingId;

	@Column
	private String returnCourierServiceName;

	@Column
	private Double returnShippingCharge;

	@Column
	private Boolean isReturnShippingNotAvailable;

	@PreRemove
	private void preRemove() {
		shippingAddress = null;
		billingAddress = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public String getReceivedPerson() {
		return receivedPerson;
	}

	public void setReceivedPerson(String receivedPerson) {
		this.receivedPerson = receivedPerson;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Boolean getIsCommonAddress() {
		return isCommonAddress;
	}

	public void setIsCommonAddress(Boolean isCommonAddress) {
		this.isCommonAddress = isCommonAddress;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public Double getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(Double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public String getTrackingName() {
		return trackingName;
	}

	public void setTrackingName(String trackingName) {
		this.trackingName = trackingName;
	}

	public String getReturnTrackingId() {
		return returnTrackingId;
	}

	public void setReturnTrackingId(String returnTrackingId) {
		this.returnTrackingId = returnTrackingId;
	}

	public String getReturnCourierServiceName() {
		return returnCourierServiceName;
	}

	public void setReturnCourierServiceName(String returnCourierServiceName) {
		this.returnCourierServiceName = returnCourierServiceName;
	}

	public Double getReturnShippingCharge() {
		return returnShippingCharge;
	}

	public void setReturnShippingCharge(Double returnShippingCharge) {
		this.returnShippingCharge = returnShippingCharge;
	}

	public Boolean getIsReturnShippingNotAvailable() {
		return isReturnShippingNotAvailable;
	}

	public void setIsReturnShippingNotAvailable(Boolean isReturnShippingNotAvailable) {
		this.isReturnShippingNotAvailable = isReturnShippingNotAvailable;
	}

}
