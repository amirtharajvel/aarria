package com.aarria.retail.web.controller;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.BankAccountService;
import com.aarria.retail.core.service.ValidationService;
import com.aarria.retail.core.util.View;
import com.aarria.retail.web.dto.request.BankAccountDto;
import com.aarria.retail.web.dto.request.DeleteBankAccountDto;
import com.aarria.retail.web.dto.response.BankAccountDetailsDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private ValidationService validationService;

	@RequestMapping(value = "/addBankAccount", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addBankAccount(BankAccountDto dto, HttpSession session) {
		User user = validationService.findUserFromSession(session);

		if (user == null) {
			return View.LOGOUT;
		}

		bankAccountService.saveBankAccount(dto, user);

		List<BankAccountDetailsDto> accounts = bankAccountService.getAllBankAccountsForUser(user);

		return new Gson().toJson(accounts);
	}

	@RequestMapping(value = "/addBankAccountFromAccount")
	public ModelAndView addBankAccountFromAccount(BankAccountDto dto, HttpSession session) {
		ModelAndView view = new ModelAndView(dto.getFromPage());

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		bankAccountService.saveBankAccount(dto, user);

		return view;
	}

	@RequestMapping(value = "/deleteBankAccount")
	public ModelAndView deleteBankAccount(DeleteBankAccountDto dto, HttpSession session) {
		ModelAndView view = new ModelAndView(dto.getFromPage());

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		bankAccountService.deleteBankAccount(dto, user);

		return view;
	}
}
