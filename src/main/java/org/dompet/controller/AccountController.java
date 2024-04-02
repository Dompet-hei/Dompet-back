package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.*;
import org.dompet.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
  private final AccountService accountService;

  @GetMapping
  public List<Account> findAllAccounts() {
    return accountService.findAllAccounts();
  }

  @GetMapping("/{accountId}")
  public Optional<Account> findAccountById(@PathVariable String accountId) {
    return accountService.findAccountById(accountId);
  }

  @PutMapping
  public Account saveAccount(@RequestBody Account account) {
    return accountService.saveAccount(account);
  }

  @DeleteMapping("/{accountId}")
  public void deleteAccountById(@PathVariable String accountId) {
    accountService.deleteAccountById(accountId);
  }

  @GetMapping("/{accountId}/about")
  public AccountView getAbout(@PathVariable String accountId) {
    return accountService.findAbout(accountId);
  }

  // Balance endpoints

  @GetMapping("/{accountId}/balance")
  public List<Balance> getBalance(@PathVariable String accountId) {
    return accountService.findBalance(accountId);
  }

  @PutMapping("/{accountId}/balance")
  public Balance crupdateBalance(@PathVariable String accountId, @RequestBody Balance balance) {
    return accountService.saveBalance(accountId, balance);
  }

  @GetMapping("/{accountId}/latestBalance")
  public Balance getLatestBalance(@PathVariable String accountId) {
    return accountService.findLatestBalance(accountId);
  }

  // Transaction endpoints
  @GetMapping("/{accountId}/transactions")
  public List<Transaction> findAllTransactionsByAccountId(@PathVariable String accountId) {
    return accountService.findAllTransactions(accountId);
  }

  @GetMapping("/{accountId}/transactions/{transactionId}")
  public Transaction findTransactionByAccountId(
      @PathVariable String accountId, @PathVariable String transactionId) {
    return accountService.findTransaction(accountId, transactionId);
  }

  @PutMapping("/{accountId}/transactions")
  public Transaction crupdateTransaction(
      @PathVariable String accountId, @RequestBody Transaction transaction) {
    return accountService.saveTransaction(accountId, transaction);
  }

  @PutMapping("/{accountId}/transactions/{transactionId}/category")
  public ResponseEntity<String> updateTransactionCategory(
      @PathVariable String accountId, @PathVariable String transactionId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body(
            "Updating transaction "
                + transactionId
                + "'s category for account "
                + accountId
                + " is not implemented yet.");
  }

  // Transfer endpoints
  @GetMapping("/{accountId}/transfers")
  public List<Transfer> findAllTransfersByAccountId(@PathVariable String accountId) {
    return accountService.findAllTransfers(accountId);
  }

  @GetMapping("/{accountId}/transfers/{transferId}")
  public Transfer findTransferByAccountId(
      @PathVariable String accountId, @PathVariable String transferId) {
    return accountService.findTransfer(accountId, transferId);
  }

  @PutMapping("/{accountId}/transfers")
  public Transfer crupdateTransfer(@PathVariable String accountId, @RequestBody Transfer transfer) {
    return accountService.saveTransfer(accountId, transfer);
  }

  @PostMapping("/{accountId}/statement")
  public List<AccountStatement> generateStatement(@PathVariable String accountId) {
    return accountService.generateStatement(accountId);
  }

  // Overdraft endpoints
  @GetMapping("/{accountId}/overdraft")
  public List<Overdraft> findOverdrafts(@PathVariable String accountId) {
    return accountService.findAllOverdrafts(accountId);
  }

  @GetMapping("/{accountId}/overdraft/{overdraftId}")
  public Overdraft findOverdraftByAccountId(
      @PathVariable String accountId, @PathVariable String overdraftId) {
    return accountService.findOverdraft(accountId, overdraftId);
  }

  @PutMapping("/{accountId}/overdraft")
  public Overdraft crupdateOverdraft(
      @PathVariable String accountId, @RequestBody Overdraft overdraft) {
    return accountService.saveOverdraft(accountId, overdraft);
  }
}
