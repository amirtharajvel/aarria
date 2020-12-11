package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.web.dto.request.LoginDto;

public interface UserService {

	public User findUserByEmail(String email, Boolean verified);

	public User findUserByMobile(String mobile, Boolean verified);

	public boolean isUserPresent(String email, String mobile);

	public Boolean isUserPresent(LoginDto dto);

	public Boolean isUserPresent(String emailOrMobile);

	public User login(LoginDto dto);

	public User findOne(Long userId);

	public User saveUser(User user);

	User getUserByEmailOrMobile(String emailOrMobile);
	
	User findOne(User user);
}
