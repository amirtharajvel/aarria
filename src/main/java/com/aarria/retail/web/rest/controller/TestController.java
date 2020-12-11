package com.aarria.retail.web.rest.controller;

import com.aarria.retail.core.service.TestService;
import com.aarria.retail.core.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class TestController {

	@Autowired
	private TestService testService;

	@RequestMapping(value = "/test")
	public void test(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("userId") == null) {
			session.setAttribute("userId", Util.generateRandomOrderId(1l));
		}
		testService.test(session);
	}

}
