package org.dompet.service;

import java.util.List;
import org.dompet.model.Account;
import org.dompet.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
  private final AccountRepository accountRepository = new AccountRepository(null);

  public Account findById(String id) {
    return accountRepository.getById(id);
  }

  public List<Account> findAll() {
    return accountRepository.getAll();
  }

  public Account save(Account account) {
    accountRepository.save(account);
    return account;
  }

  public void deleteById(String id) {
    accountRepository.deleteById(id);
  }
}
