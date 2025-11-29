package com.banking.account.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
				.orElseThrow(()->new RuntimeException("Account Does not Exist!"));
		double totalAmount=pressentAccount.getBalance()+amount;
		pressentAccount.setBalance(totalAmount);
		Account savedAccount=accountRepository.save(pressentAccount);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDTO withDraw(Long id, double amount) {
		Account  pressentAccount=accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account Does not Exist!"));
		if(amount>pressentAccount.getBalance()) {
			throw new RuntimeException("Insufficient Balance!");
		}
		double totalAmount=pressentAccount.getBalance()-amount;
		pressentAccount.setBalance(totalAmount);
		Account savedAccount=accountRepository.save(pressentAccount);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDTO> getAllAccounts() {
		return accountRepository.findAll()
				.stream()
				.map(AccountMapper::mapToAccountDto)
				.collect(Collectors.toList());
	}


	@Override
	public AccountDTO updateAccount(@PathVariable AccountDTO account) {
		Account  pressentAccount=accountRepository
				.findById(account.getId())
				.orElseThrow(()->new RuntimeException("Account Does not Exist!"));
		pressentAccount.setAccountHolderName(account.getAccountHolderName());
		pressentAccount.setBalance(account.getBalance());
		accountRepository.save(pressentAccount);
		return AccountMapper.mapToAccountDto(pressentAccount);
	}


	@Override
	public AccountDTO deleteAccount(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exists!"));
		 accountRepository.deleteById(id);
		 return AccountMapper.mapToAccountDto(account);
		
	}
	
	

}
