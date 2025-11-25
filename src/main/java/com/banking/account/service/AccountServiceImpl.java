package com.banking.account.service;

import org.springframework.stereotype.Service;

import com.banking.account.dto.AccountDTO;
import com.banking.account.entity.Account;
import com.banking.account.mapper.AccountMapper;
import com.banking.account.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDTO addAccount(AccountDTO accountDto) {
		
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDTO getAccountById(Long id) {
		
		Account recievedAccount=accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Employee Does not Exist!"));
		return AccountMapper.mapToAccountDto(recievedAccount);
	}


	@Override
	public AccountDTO deposit(Long id, double amount) {
		
		Account pressentAccount=accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Employee Does not Exist!"));
		double totalAmount=pressentAccount.getBalance()+amount;
		pressentAccount.setBalance(totalAmount);
		Account savedAccount=accountRepository.save(pressentAccount);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDTO withDraw(Long id, double amount) {
		Account  pressentAccount=accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Employee Does not Exist!"));
		if(amount>pressentAccount.getBalance()) {
			throw new RuntimeException("Insufficient Balance!");
		}
		double totalAmount=pressentAccount.getBalance()-amount;
		pressentAccount.setBalance(totalAmount);
		Account savedAccount=accountRepository.save(pressentAccount);
		return AccountMapper.mapToAccountDto(savedAccount);
	}
	
	

}
