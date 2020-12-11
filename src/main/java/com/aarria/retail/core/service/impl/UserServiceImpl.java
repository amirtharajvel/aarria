package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.UserService;
import com.aarria.retail.core.util.Constants;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.persistence.repository.UserRepository;
import com.aarria.retail.web.dto.request.LoginDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private static Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository repository;

	@Override
	public User findUserByEmail(String email, Boolean verified) {
		return repository.findByEmailAndEmailVerified(email, verified);
	}

	@Override
	public User findUserByMobile(String mobile, Boolean verified) {
		return repository.findByMobileAndMobileVerified(mobile, verified);
	}

	@Override
	public boolean isUserPresent(String email, String mobile) {
		if (this.findUserByEmail(email, Constants.VERIFIED) != null
				|| this.findUserByMobile(mobile, Constants.VERIFIED) != null) {
			return true;
		}

		return false;
	}

	@Override
	public User login(LoginDto dto) {
		User user = null;
		try {
			user = repository.findByMobileAndMobileVerified(dto.getMobile(), Constants.VERIFIED);
			if (user != null) {
				boolean authenticated = Util.decodePassword(dto.getPassword(), user.getPassword());
				if (authenticated) {
					return user;
				}
			}
		} catch (Exception e) {
			LOGGER.info("Exception while login");
		}

		return null;
	}

	@Override
	public Boolean isUserPresent(LoginDto dto) {
		try {
			if (this.findUserByMobile(dto.getMobile(), Constants.VERIFIED) != null) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in isUserPresent");
		}

		return false;
	}

	@Override
	public User findOne(Long userId) {
		return repository.findById(userId).get();
	}

	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}

	@Override
	public Boolean isUserPresent(String emailOrMobile) {
		try {
			Long.parseLong(emailOrMobile);
			if (this.findUserByMobile(emailOrMobile, Constants.VERIFIED) != null) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("exception in isUserPresent " + e);
		}

		return false;
	}

	@Override
	public User getUserByEmailOrMobile(String emailOrMobile) {
		try {
			Long.parseLong(emailOrMobile);
			return this.findUserByMobile(emailOrMobile, Constants.VERIFIED);
		} catch (NumberFormatException e) {
			return findUserByEmail(emailOrMobile, Constants.VERIFIED);
		}
	}

	@Override
	public User findOne(User user) {
		return repository.findById(user.getId()).get();
	}
}
