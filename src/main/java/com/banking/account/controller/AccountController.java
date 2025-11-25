package com.banking.account.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.account.dto.AccountDTO;
import com.banking.account.service.AccountService;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
	AccountService service;

	public AccountController(AccountService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDto) {
		return new ResponseEntity<>(service.addAccount(accountDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id){
		return ResponseEntity.ok(service.getAccountById(id));
	}
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDTO> deposit(@PathVariable Long id, @RequestBody Map<String, Long> request){
		double amount=request.get("amount");
		return ResponseEntity.ok(service.deposit(id, amount));
	}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id, @RequestBody Map<String, Long> request){
		double amount=request.get("amount");
		return ResponseEntity.ok(service.withDraw(id, amount));
	}
}
