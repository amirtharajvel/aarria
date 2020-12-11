package com.aarria.retail.core.domain;

import com.aarria.retail.core.util.Enum.SignupType;
import com.aarria.retail.core.util.Util;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@TableGenerator(name = "user_generator", initialValue = 0, allocationSize = 1)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_generator")
	private Long id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String alternateEmail;

	@Column(unique = true)
	private String mobile;

	@Column
	private String alternateMobile;

	@Column
	private String password;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginDate;

	@Column
	private Boolean mobileVerified;

	@Column
	private Boolean emailVerified;

	/** whether the user registered manually or registered by shipping **/
	@Column
	private Integer registerType;

	@OneToMany(mappedBy = "user")
	private List<Address> addresses;

	@Column
	private Boolean isAdmin;

	public static User createUser(String mobile, String email, String password) {
		User newUser = new User();

		newUser.setEmail(email);
		newUser.setMobile(mobile);
		newUser.setCreatedDate(new Date());
		newUser.setMobileVerified(true);
		newUser.setRegisterType(SignupType.CHECKOUT.ordinal());
		newUser.setPassword(Util.encodePassword(password));
		newUser.setName("Sample Name");

		return newUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAlternateMobile() {
		return alternateMobile;
	}

	public void setAlternateMobile(String alternateMobile) {
		this.alternateMobile = alternateMobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getRegisterType() {
		return registerType;
	}

	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getMobileVerified() {
		return mobileVerified;
	}

	public void setMobileVerified(Boolean mobileVerified) {
		this.mobileVerified = mobileVerified;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
