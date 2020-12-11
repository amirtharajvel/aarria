package com.aarria.retail.core.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.aarria.retail.core.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Override
	public void test(HttpSession session) {
		System.out.println(this.hashCode() + " || " + session.hashCode() + " || userId = " + session.getAttribute("userId"));
	}
}
