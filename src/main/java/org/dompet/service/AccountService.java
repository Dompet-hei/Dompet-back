package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Account;
import org.dompet.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
  public final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account save(Account account) {
    return accountRepository.save(account);
  }

  public Optional<Account> findById(String id) {
    return accountRepository.findById(id);
  }

  public List<Account> findAll() {
    return accountRepository.findAll();
  }

  public void deleteById(String id) {
    accountRepository.deleteById(id);
  }
}
