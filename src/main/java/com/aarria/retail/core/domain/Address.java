package com.aarria.retail.core.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "address")
@TableGenerator(name = "address_generator", initialValue = 0, allocationSize = 1)
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "address_generator")
	private Long id;

	@Column
	private String state;

	@Column
	private String address;

	@Column(length = 7)
	private Integer pincode;

	@Column
	private String landmark;

	@Column
	private String name;

	@Column
	private String mobile;

	@ManyToOne(targetEntity = User.class)
	private User user;

	@Column
	private Integer country;

	@Column(name="isDeliverHere")
	private Boolean isDeliverHere;

	@Column
	private String email;

	@Column(name="deliveryArea")
	private String deliveryArea;
	
	@Column
	private String coordinates;
	
	@Column(name = "addedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedDate;

	@Column(name = "updatedDate", columnDefinition = "TIMESTAMP (6)")
	private Timestamp updatedDate = Timestamp.from(Instant.now());

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getIsDeliverHere() {
		return isDeliverHere;
	}

	public void setIsDeliverHere(Boolean isDeliverHere) {
		this.isDeliverHere = isDeliverHere;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeliveryArea() {
		return deliveryArea;
	}

	public void setDeliveryArea(String deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	
}
