package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.domain.Wallet;
import com.aarria.retail.web.dto.response.WalletDetailsDto;

import java.util.List;

public interface WalletService {

	public Wallet findByUser(User user);
	
	public void depositAmount(Double amount, User user, Order order);
	
	public List<WalletDetailsDto> findAll();

	void deductAmount(Double amount, User user, Order order);
}
