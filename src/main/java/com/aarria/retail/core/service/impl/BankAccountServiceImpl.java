package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.BankAccount;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.BankAccountService;
import com.aarria.retail.persistence.repository.BankAccountRepository;
import com.aarria.retail.web.dto.request.BankAccountDto;
import com.aarria.retail.web.dto.request.DeleteBankAccountDto;
import com.aarria.retail.web.dto.response.BankAccountDetailsDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Override
	public List<BankAccountDetailsDto> getAllBankAccountsForUser(User user) {
		List<BankAccount> accounts = bankAccountRepository.findByUser(user);
		if (CollectionUtils.isNotEmpty(accounts)) {

			List<BankAccountDetailsDto> dtos = new ArrayList<>();
			for (BankAccount account : accounts) {
				BankAccountDetailsDto dto = new BankAccountDetailsDto(account.getAccountNo(), account.getIfscCode(),
						account.getId());
				dtos.add(dto);
			}

			return dtos;
		}

		return null;
	}

	@Override
	public void saveBankAccount(BankAccountDto dto, User user) {
		BankAccount account = dto.createBankAccount(user);
		bankAccountRepository.save(account);
	}

	@Override
	public void deleteBankAccount(DeleteBankAccountDto dto, User user) {
		List<BankAccount> accounts = bankAccountRepository.findByIfscCodeAndUser(
				dto.getIfscCode(), user);
		if (CollectionUtils.isNotEmpty(accounts)) {
			accounts.forEach(a -> {
				if (dto.getIdentifier().equals(a.getId())) {
					bankAccountRepository.delete(a);
				}
			});
		}
	}

}
