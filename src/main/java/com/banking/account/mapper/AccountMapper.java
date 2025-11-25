package com.banking.account.mapper;

import com.banking.account.dto.AccountDTO;
import com.banking.account.entity.Account;

public class AccountMapper {
//	Method to convert from AccountDTO to Account
	public static Account mapToAccount(AccountDTO accountDto) {
		Account account=new Account(
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance()
				);
		return account;
		
	}
	
//	Method to convert from Account to AccountDTO
	public static AccountDTO mapToAccountDto(Account account) {
		AccountDTO accountDto=new AccountDTO(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		return accountDto;
	}
}
