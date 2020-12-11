package com.aarria.retail.web.dto.request;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.util.Constants;
import com.aarria.retail.core.util.Util;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class RegisterInputDto {

	@Email
	@NotBlank
	@Size(min = 1, max = 100)
	private String email;

	@NotBlank
	@Pattern(regexp = "[\\d]{10}")
	private String mobile;

	@NotBlank
	@Size(min = 1, max = 25)
	private String password;

	public User getUser() {

		User user = new User();
		user.setCreatedDate(new Date());
		user.setEmail(email);
		user.setMobile(mobile.toString());
		user.setPassword(Util.encodePassword(password));
		user.setMobileVerified(Constants.NOT_VERIFIED);
		user.setEmailVerified(Constants.NOT_VERIFIED);
		return user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegisterInputDto [email=" + email + ", mobile=" + mobile + ", password=" + "]";
	}

}
