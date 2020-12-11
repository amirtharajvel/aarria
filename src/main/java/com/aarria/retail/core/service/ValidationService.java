package com.aarria.retail.core.service;

import javax.servlet.http.HttpSession;

import com.aarria.retail.core.domain.User;

public interface ValidationService {

	public User findUserFromSession(HttpSession session);

	User findAdminUserFromSession(HttpSession session);
}
