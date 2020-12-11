package com.aarria.retail.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "pincode")
@TableGenerator(name = "pincode_generator", initialValue = 0, allocationSize = 1)
public class Pincode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pincode_generator")
	private Long id;

	@Column
	private String officename;

	@Column(unique = true)
	private String pincode;

	@Column
	private String officeType;

	@Column
	private String DeliveryStatus;

	@Column
	private String divisionname;

	@Column
	private String regionname;

	@Column
	private String circlename;

	@Column
	private String Taluk;

	@Column
	private String Districtname;

	@Column
	private String statename;

	public Pincode() {
		super();
	}

	public Pincode(String officename, String pincode, String officeType, String deliveryStatus, String divisionname,
			String regionname, String circlename, String taluk, String districtname, String statename) {
		super();
		this.officename = officename;
		this.pincode = pincode;
		this.officeType = officeType;
		DeliveryStatus = deliveryStatus;
		this.divisionname = divisionname;
		this.regionname = regionname;
		this.circlename = circlename;
		Taluk = taluk;
		Districtname = districtname;
		this.statename = statename;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOfficename() {
		return officename;
	}

	public void setOfficename(String officename) {
		this.officename = officename;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getOfficeType() {
		return officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	public String getDeliveryStatus() {
		return DeliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		DeliveryStatus = deliveryStatus;
	}

	public String getDivisionname() {
		return divisionname;
	}

	public void setDivisionname(String divisionname) {
		this.divisionname = divisionname;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getCirclename() {
		return circlename;
	}

	public void setCirclename(String circlename) {
		this.circlename = circlename;
	}

	public String getTaluk() {
		return Taluk;
	}

	public void setTaluk(String taluk) {
		Taluk = taluk;
	}

	public String getDistrictname() {
		return Districtname;
	}

	public void setDistrictname(String districtname) {
		Districtname = districtname;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	@Override
	public String toString() {
		return "Pincode [officename=" + officename + ", pincode=" + pincode + ", officeType=" + officeType
				+ ", DeliveryStatus=" + DeliveryStatus + ", divisionname=" + divisionname + ", regionname=" + regionname
				+ ", circlename=" + circlename + ", Taluk=" + Taluk + ", Districtname=" + Districtname + ", statename="
				+ statename + "]";
	}

	
}
