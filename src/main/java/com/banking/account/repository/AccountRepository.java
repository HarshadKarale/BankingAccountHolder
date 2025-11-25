package com.banking.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.account.entity.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	

}
