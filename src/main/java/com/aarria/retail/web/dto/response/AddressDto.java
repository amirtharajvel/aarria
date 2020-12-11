package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Address;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.util.Enum;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

public class AddressDto {

	private Long id;

	private String name;

	private String address;

	private Integer pincode;

	private String landmark;

	private String country;

	private String mobile;

	private String email;

	private String deliveryArea;

	private String coordinates;

	public AddressDto() {
		super();
	}

	public AddressDto(Long id, String name, String address, Integer pincode, String landmark, String country,
			String mobile, String email, String deliveryArea, String coordinates) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.pincode = pincode;

		if (country == null) {
			country = "";
		}
		if (landmark == null) {
			landmark = "";
		}

		this.landmark = landmark;
		this.country = country;
		this.mobile = mobile;
		this.email = email;
		this.deliveryArea = deliveryArea;
		this.coordinates = coordinates;
	}

	public static AddressDto createDtoFromAddress(Address a) {
		if (a == null) {
			return null;
		}
		return new AddressDto(a.getId(), a.getName(), a.getAddress(), a.getPincode(), a.getLandmark(),
				Enum.Country.getString(a.getCountry()), a.getMobile(), a.getEmail(), a.getDeliveryArea(),
				a.getCoordinates());
	}

	public Address getAddressInstance(User user) {
		Address instance = new Address();
		instance.setAddedDate(new Date());
		instance.setAddress(address);
		instance.setCountry(Enum.Country.INDIA.ordinal());
		instance.setLandmark(landmark);
		instance.setMobile(mobile);
		instance.setName(name);
		instance.setPincode(pincode);
		instance.setUpdatedDate(Timestamp.from(Instant.now()));
		instance.setUser(user);
		instance.setIsDeliverHere(true);
		instance.setEmail(email);
		instance.setDeliveryArea(deliveryArea);
		instance.setCoordinates(coordinates);

		return instance;
	}

	public Address getUpdateAddressInstance(Address instance, User user) {
		instance.setId(this.getId());
		instance.setAddress(this.getAddress());
		instance.setLandmark(this.getLandmark());
		instance.setMobile(this.getMobile());
		instance.setName(this.getName());
		instance.setPincode(this.getPincode());
		instance.setUpdatedDate(Timestamp.from(Instant.now()));
		instance.setUser(user);
		instance.setDeliveryArea(this.getDeliveryArea());
		instance.setEmail(email);

		return instance;
	}

	public Address getInstance(Address instance) {
		instance.setAddress(address);
		instance.setCountry(Enum.Country.INDIA.ordinal());
		instance.setLandmark(landmark);
		instance.setMobile(mobile);
		instance.setName(name);
		instance.setPincode(pincode);
		instance.setUpdatedDate(Timestamp.from(Instant.now()));
		instance.setEmail(email);

		return instance;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return name + "," + address + ", pincode=" + pincode + ", landmark=" + landmark + ", country=" + country
				+ ", mobile=" + mobile + ", email=" + email;
	}
}
