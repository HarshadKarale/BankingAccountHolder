package com.banking.account.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.banking.account.dto.AccountDTO;
import com.banking.account.entity.Account;

public interface AccountService {
	AccountDTO addAccount(AccountDTO accountDto);
	AccountDTO getAccountById(Long id);
	AccountDTO deposit(Long id, double amount);
	AccountDTO withDraw(Long id, double amount);
	List<AccountDTO> getAllAccounts();
	AccountDTO updateAccount(@PathVariable AccountDTO account);
	public AccountDTO deleteAccount(Long id);
}
