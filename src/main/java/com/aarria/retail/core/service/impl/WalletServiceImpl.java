package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.domain.Wallet;
import com.aarria.retail.core.service.MessageService;
import com.aarria.retail.core.service.WalletService;
import com.aarria.retail.persistence.repository.WalletRepository;
import com.aarria.retail.web.dto.response.WalletDetailsDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

	private static final String DEDUCTED = "Deducted from ";

	private static final String REFUNDED = "Refunded to ";

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private MessageService messageService;

	@Override
	public Wallet findByUser(User user) {
		return walletRepository.findByUser(user);
	}

	@Override
	public void depositAmount(Double amount, User user, Order order) {

		if (order == null) {
//			messageService.sendEmailAndSmsToAdmin(amount + "" + user + " tried to deposit amount but order is null",
//					"Order is null while depositing amount to Wallet. Please check on an emergency basis.");
			//TODO
			return;
		}

		Wallet wallet = findByUser(user);

		if (wallet == null) {
			wallet = new Wallet();
			wallet.setUser(user);
		}
		wallet.setAmount(wallet.getAmount() + amount);
		wallet.setUpdatedDate(new Date());
		walletRepository.save(wallet);
		messageService.sendMoneyDepositedToWalletMessage(user, amount, order, REFUNDED);
	}

	@Override
	public void deductAmount(Double amount, User user, Order order) {

		if (order == null) {
//			messageService.sendEmailAndSmsToAdmin(amount + "" + user + " tried to deduct amount but order is null",
//					"Order is null while deducting amount to Wallet. Please check on an emergency basis.");
			//TODO
			return;
		}

		Wallet wallet = findByUser(user);

		if (wallet == null) {
//			messageService.sendEmailAndSmsToAdmin(amount + "" + user + " tried to deduct amount but wallet is null",
//					"Wallet is null while deducting from wallet");
			//TODO
			return;
		}

		wallet.setAmount(wallet.getAmount() - amount);
		wallet.setUpdatedDate(new Date());
		walletRepository.save(wallet);
		messageService.sendMoneyDepositedToWalletMessage(user, amount, order, DEDUCTED);
	}

	@Override
	public List<WalletDetailsDto> findAll() {
		List<Wallet> wallets = walletRepository.findAll();
		List<WalletDetailsDto> dtos = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(wallets)) {
			for (Wallet w : wallets) {
				
				if(w == null || w.getUser() == null) {
					continue;
				}
				
				WalletDetailsDto dto = new WalletDetailsDto(w.getUser().getId(), w.getUser().getMobile(),
						w.getUpdatedDate() + "", w.getAmount());
				dtos.add(dto);
			}
		}

		return dtos;
	}
}
