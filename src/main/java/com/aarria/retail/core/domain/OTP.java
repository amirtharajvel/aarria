package com.aarria.retail.core.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "otp")
@TableGenerator(name = "otp_generator", initialValue = 0, allocationSize = 1)
public class OTP {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "otp_generator")
	private Long id;

	@Column
	private String activity;

	@Column
	private String mobile;

	@Column
	private Integer count;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public OTP() {
		super();
	}

	public OTP(Integer count, String mobile, String activity, Date date) {
		super();
		this.count = count;
		this.mobile = mobile;
		this.activity = activity;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "OTP [id=" + id + ", activity=" + activity + ", mobile=" + mobile + ", count=" + count + ", date=" + date
				+ "]";
	}

}
