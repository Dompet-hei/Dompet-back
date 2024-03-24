package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.*;
import org.dompet.repository.*;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
  public final AccountRepository accountRepository;

  public final AccountBalanceRepository accountBalanceRepository;
  public final AccountViewRepository accountViewRepository;

  public final TransactionRepository transactionRepository;
  public final TransferRepository transferRepository;

  public final OverdraftRepository overdraftRepository;

  public AccountService(
      AccountRepository accountRepository,
      AccountBalanceRepository accountBalanceRepository,
      AccountViewRepository accountViewRepository,
      TransactionRepository transactionRepository,
      TransferRepository transferRepository,
      OverdraftRepository overdraftRepository) {
    this.accountRepository = accountRepository;
    this.accountBalanceRepository = accountBalanceRepository;
    this.accountViewRepository = accountViewRepository;
    this.transactionRepository = transactionRepository;
    this.transferRepository = transferRepository;
    this.overdraftRepository = overdraftRepository;
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

  public AccountView findAbout(String accountId) {
    return accountViewRepository.findByAccountId(accountId);
  }

  public AccountBalance findBalance(String accountId) {
    return accountBalanceRepository.findByAccountId(accountId);
  }

  // ==============================================================================
  public List<Transaction> findTransactions(String AccountId) {
    return transactionRepository.findByAccountId(AccountId);
  }

  public Transaction findTransaction(String accountId, String transactionId) {
    return transactionRepository.findByAccountIdAndTransactionId(accountId, transactionId);
  }

  // ===============================================================================

  public List<Transfer> findTransfers(String senderAccountId) {
    return transferRepository.findBySenderAccountId(senderAccountId);
  }

  public Transfer findTransfer(String senderAccountId, String transferId) {
    return transferRepository.findBySenderAccountIdAndTransferId(senderAccountId, transferId);
  }

  // ===============================================================================

  public List<Overdraft> findOverdrafts(String accountId) {
    return overdraftRepository.findByAccountId(accountId);
  }

  public Overdraft findOverdraft(String accountId, String overdraftId) {
    return overdraftRepository.findByAccountIdAndOverdraftId(accountId, overdraftId);
  }

  // ===============================================================================
}
