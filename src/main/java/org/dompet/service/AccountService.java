package org.dompet.service;

import java.util.List;
import org.dompet.model.Account;
import org.dompet.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
  private final AccountRepository accountRepository = new AccountRepository();

  public void createAccount(Account account) {
    accountRepository.insert(account, true);
  }

  public Account getAccountById(Integer id) {
    return accountRepository.getById(id);
  }

  public List<Account> getAllAccounts() {
    return accountRepository.getAll();
  }

  public void updateAccount(Account account) {
    accountRepository.save(account);
  }

  public void deleteAccountById(Integer id) {
    accountRepository.deleteById(id);
  }
}
