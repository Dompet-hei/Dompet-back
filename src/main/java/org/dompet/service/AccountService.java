package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.*;
import org.dompet.repository.*;
import org.dompet.utils.EntityUtil;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
  public final AccountRepository accountRepository;
  public final BalanceRepository balanceRepository;
  public final AccountViewRepository accountViewRepository;
  public final AccountStatementRepository accountStatementRepository;
  public final TransactionRepository transactionRepository;
  public final TransferRepository transferRepository;
  public final OverdraftRepository overdraftRepository;

  public AccountService(
      AccountRepository accountRepository,
      AccountViewRepository accountViewRepository,
      BalanceRepository balanceRepository,
      AccountStatementRepository accountStatementRepository,
      TransactionRepository transactionRepository,
      TransferRepository transferRepository,
      OverdraftRepository overdraftRepository) {
    this.accountRepository = accountRepository;
    this.accountViewRepository = accountViewRepository;
    this.balanceRepository = balanceRepository;
    this.accountStatementRepository = accountStatementRepository;
    this.transactionRepository = transactionRepository;
    this.transferRepository = transferRepository;
    this.overdraftRepository = overdraftRepository;
  }

  public Account saveAccount(Account account) {
    return accountRepository.save(account);
  }

  public Optional<Account> findAccountById(String id) {
    return accountRepository.getById(id);
  }

  public List<Account> findAllAccounts() {
    return accountRepository.getAll();
  }

  public void deleteAccountById(String id) {
    accountRepository.deleteById(id);
  }

  public AccountView findAbout(String accountId) {
    return accountViewRepository.findByAccountId(accountId);
  }

  // ==============================================================================

  public List<Balance> findBalance(String accountId) {
    return balanceRepository.findAllByAccountId(accountId);
  }

  public Balance findLatestBalance(String accountId) {
    return balanceRepository.findFirstByAccountIdOrderByLastUpdatedDesc(accountId);
  }

  public Balance saveBalance(String accountId, Balance balance) {
    if (balance == null) {
      throw new IllegalArgumentException("Balance cannot be null");
    }
    accountRepository
        .findById(accountId)
        .orElseThrow(() -> new RuntimeException("Account not found"));
    Balance currentBalance = balanceRepository.findById(balance.getBalanceId()).orElse(null);
    if (currentBalance == null) {
      balance.setAccountId(accountId);
      return balanceRepository.save(balance);
    }
    EntityUtil.updateEntityFields(currentBalance, balance);
    return balanceRepository.save(currentBalance);
  }

  // ==============================================================================

  public List<Transaction> findAllTransactions(String accountId) {
    return transactionRepository.findAllByAccountId(accountId);
  }

  public Transaction findTransaction(String accountId, String transactionId) {
    return transactionRepository.findByAccountIdAndTransactionId(accountId, transactionId);
  }

  public Transaction saveTransaction(String accountId, Transaction transaction) {
    if (transaction == null) {
      throw new IllegalArgumentException("Transaction cannot be null");
    }
    accountRepository
        .findById(accountId)
        .orElseThrow(() -> new RuntimeException("Account not found"));

    if (transactionRepository.findById(transaction.getTransactionId()).isEmpty()) {
      transaction.setAccountId(accountId);
      return transactionRepository.save(transaction);
    }
    Transaction existingTransaction =
        transactionRepository
            .findById(transaction.getTransactionId())
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
    EntityUtil.updateEntityFields(existingTransaction, transaction);
    return transactionRepository.save(existingTransaction);
  }

  // ==============================================================================

  public List<Transfer> findAllTransfers(String senderAccountId) {
    return transferRepository.findAllBySenderAccountId(senderAccountId);
  }

  public Transfer findTransfer(String senderAccountId, String transferId) {
    return transferRepository.findBySenderAccountIdAndTransferId(senderAccountId, transferId);
  }

  public Transfer saveTransfer(String senderAccountId, Transfer transfer) {
    if (transfer == null) {
      throw new IllegalArgumentException("Transfer cannot be null");
    }
    accountRepository
        .findById(senderAccountId)
        .orElseThrow(() -> new RuntimeException("Account not found"));

    if (transferRepository.findById(transfer.getTransferId()).isEmpty()) {
      transfer.setSenderAccountId(senderAccountId);
      return transferRepository.save(transfer);
    }
    Transfer existingTransfer =
        transferRepository
            .findById(transfer.getTransferId())
            .orElseThrow(() -> new RuntimeException("Transfer not found"));
    EntityUtil.updateEntityFields(existingTransfer, transfer);
    return transferRepository.save(existingTransfer);
  }

  // ==============================================================================

  public List<Overdraft> findAllOverdrafts(String accountId) {
    return overdraftRepository.findByAccountId(accountId);
  }

  public Overdraft findOverdraft(String accountId, String overdraftId) {
    return overdraftRepository.findByAccountIdAndOverdraftId(accountId, overdraftId);
  }

  public Overdraft saveOverdraft(String accountId, Overdraft overdraft) {
    if (overdraft == null) {
      throw new IllegalArgumentException("Overdraft cannot be null");
    }
    accountRepository
        .findById(accountId)
        .orElseThrow(() -> new RuntimeException("Account not found"));

    if (overdraftRepository.findById(overdraft.getOverdraftId()).isEmpty()) {
      overdraft.setAccountId(accountId);
      return overdraftRepository.save(overdraft);
    }
    Overdraft existingOverdraft =
        overdraftRepository
            .findById(overdraft.getOverdraftId())
            .orElseThrow(() -> new RuntimeException("Overdraft not found"));
    EntityUtil.updateEntityFields(existingOverdraft, overdraft);
    return overdraftRepository.save(existingOverdraft);
  }

  // ==============================================================================

  public List<AccountStatement> generateStatement(String accountId) {
    return accountStatementRepository.findAllByAccountId(accountId);
  }
}
