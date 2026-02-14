package com.banking.account.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.banking.account.dto.AccountDTO;
import com.banking.account.entity.Account;
import com.banking.account.exception.AccountNotFoundException;
import com.banking.account.exception.InSufficientBalanceException;
import com.banking.account.exception.NegativeAmountException;
import com.banking.account.mapper.AccountMapper;
import com.banking.account.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


//	To add the new account
	@Override
	public AccountDTO addAccount(AccountDTO accountDto) {
		
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	
//	To get the account based on the Id
	@Override
	public AccountDTO getAccountById(Long id) {
		
		Account recievedAccount=accountRepository
				.findById(id)
				.orElseThrow(()->new AccountNotFoundException("Employee Does not Exist!"));
		return AccountMapper.mapToAccountDto(recievedAccount);
	}


//	To deposit amount from account based on the id
	@Override
	public AccountDTO deposit(Long id, double amount) {
		
		Account pressentAccount=accountRepository
				.findById(id)
				.orElseThrow(()->new AccountNotFoundException("Account Does not Exist!"));
		double totalAmount=pressentAccount.getBalance()+amount;
		pressentAccount.setBalance(totalAmount);
		Account savedAccount=accountRepository.save(pressentAccount);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


//	To withdraw amount from account based on the id
	@Override
	public AccountDTO withDraw(Long id, double amount) {
		Account  pressentAccount=accountRepository
				.findById(id)
				.orElseThrow(()->new AccountNotFoundException("Account Does not Exist!"));
		if(amount<0) {
			throw new NegativeAmountException("Please Enter Valid Amount!");
		}
		else if(amount>pressentAccount.getBalance()) {
			throw new InSufficientBalanceException("Insufficient Balance!");
		}
		double totalAmount=pressentAccount.getBalance()-amount;
		pressentAccount.setBalance(totalAmount);
		Account savedAccount=accountRepository.save(pressentAccount);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

//	To get all accounts in db
	@Override
	public List<AccountDTO> getAllAccounts() {
		return accountRepository.findAll()
				.stream()
				.map(AccountMapper::mapToAccountDto)
				.collect(Collectors.toList());
	}


//	To update the account based on recieved json Data
	@Override
	public AccountDTO updateAccount(@PathVariable AccountDTO account) {
		Account  pressentAccount=accountRepository
				.findById(account.getId())
				.orElseThrow(()->new AccountNotFoundException("Account Does not Exist!"));
		pressentAccount.setAccountHolderName(account.getAccountHolderName());
		pressentAccount.setBalance(account.getBalance());
		accountRepository.save(pressentAccount);
		return AccountMapper.mapToAccountDto(pressentAccount);
	}


//	To delete the account based on Id and returning the deleted account
	@Override
	public AccountDTO deleteAccount(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(()->new AccountNotFoundException("Account does not exists!"));
		 accountRepository.deleteById(id);
		 return AccountMapper.mapToAccountDto(account);
		
	}
	
	

}
