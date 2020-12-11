package com.aarria.retail.core.service;

import java.util.List;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.web.dto.request.BankAccountDto;
import com.aarria.retail.web.dto.request.DeleteBankAccountDto;
import com.aarria.retail.web.dto.response.BankAccountDetailsDto;

public interface BankAccountService {

	void deleteBankAccount(DeleteBankAccountDto dto, User user);
	void saveBankAccount(BankAccountDto dto, User user);
	List<BankAccountDetailsDto> getAllBankAccountsForUser(User user);
}
