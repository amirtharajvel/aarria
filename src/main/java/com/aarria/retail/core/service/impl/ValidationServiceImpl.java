package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.MessageService;
import com.aarria.retail.core.service.UserService;
import com.aarria.retail.core.service.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ValidationServiceImpl implements ValidationService {

	private static Logger LOGGER = LogManager.getLogger(ValidationServiceImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Override
	public User findUserFromSession(HttpSession session) {

		if (session == null || session.getAttribute("userId") == null) {
			return null;
		}

		Long userId = null;
		try {
			userId = Long.parseLong(session.getAttribute("userId").toString());
		} catch (Exception e) {
			messageService.sendEmailAndSmsToAdmin(null, "In session user id is not long value" + "Not long value");
			LOGGER.error("How come in session an userId is not a Long value.?!", e);
			return null;
		}

		return userService.findOne(userId);

	}

	@Override
	public User findAdminUserFromSession(HttpSession session) {

		if (session == null || session.getAttribute("userId") == null) {
			return null;
		}

		Long userId = null;
		try {
			userId = Long.parseLong(session.getAttribute("userId").toString());
		} catch (Exception e) {
			messageService.sendEmailAndSmsToAdmin(null, "In session user id is not long vaue" + "Not long value");
			LOGGER.error("How come in session an userId is not a Long value.?!", e);
			return null;
		}

		User user = userService.findOne(userId);

		if (user == null) {
			return null;
		}

		if (user.getIsAdmin() != null && user.getIsAdmin().equals(true)) {
			return user;
		}

		return null;

	}

}
