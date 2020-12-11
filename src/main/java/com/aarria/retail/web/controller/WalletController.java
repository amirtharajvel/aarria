package com.aarria.retail.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.domain.Wallet;
import com.aarria.retail.core.service.ValidationService;
import com.aarria.retail.core.service.WalletService;

@Controller
public class WalletController {

	@Autowired
	private WalletService walletService;

	@Autowired
	private ValidationService validationService;
	
	@RequestMapping(value = "/getWallet")
	public @ResponseBody String getWallet(HttpSession session) {

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			return "unauthorized";
		}

		Wallet wallet = walletService.findByUser(user);

		if (wallet == null) {
			return "walletnotexists";
		}

		if (wallet.getAmount() == null) {
			return "noamount";
		}

		return wallet.getAmount().toString();
	}
}
