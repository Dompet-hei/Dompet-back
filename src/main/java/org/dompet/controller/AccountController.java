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
@CrossOrigin
@RequestMapping("/account")
public class AccountController {
  private final AccountService accountService;

  @GetMapping
  public List<Account> findAllAccounts() {
    return accountService.findAll();
  }

  @GetMapping("/{accountId}")
  public Optional<Account> findAccountById(@PathVariable String accountId) {
    return accountService.findById(accountId);
  }

  @PutMapping
  public Account saveAccount(@RequestBody Account account) {
    return accountService.save(account);
  }

  @DeleteMapping("/{accountId}")
  public void deleteAccountById(@PathVariable String accountId) {
    accountService.deleteById(accountId);
  }

  @GetMapping("/{accountId}/balance")
  public ResponseEntity<String> getBalance(@PathVariable String accountId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body("Retrieving balance for account " + accountId + " is not implemented yet.");
  }

  @GetMapping("/{accountId}/about")
  public ResponseEntity<String> getAbout(@PathVariable String accountId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
            .body("The about information for account " + accountId + " is not implemented yet.");
  }

  // Transaction endpoints
  @GetMapping("/{accountId}/transactions")
  public ResponseEntity<String> findAllTransactionsByAccountId(@PathVariable String accountId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body("Retrieving all transactions for account " + accountId + " is not implemented yet.");
  }

  @GetMapping("/{accountId}/transactions/{transactionId}")
  public ResponseEntity<String> findTransactionById(
      @PathVariable String accountId, @PathVariable String transactionId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body(
            "Retrieving transaction "
                + transactionId
                + " for account "
                + accountId
                + " is not implemented yet.");
  }

  @PostMapping("/{accountId}/transactions")
  public ResponseEntity<String> saveTransaction(
      @PathVariable String accountId, @RequestBody Transaction transaction) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body("Saving a new transaction for account " + accountId + " is not implemented yet.");
  }

  @PutMapping("/{accountId}/transactions/{transactionId}")
  public ResponseEntity<String> updateTransaction(
      @PathVariable String accountId, @PathVariable String transactionId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body(
            "Updating transaction "
                + transactionId
                + " for account "
                + accountId
                + " is not implemented yet.");
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

  @DeleteMapping("/{accountId}/transactions/{transactionId}")
  public ResponseEntity<String> deleteTransaction(
      @PathVariable String accountId, @PathVariable String transactionId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body(
            "Deleting transaction "
                + transactionId
                + " for account "
                + accountId
                + " is not implemented yet.");
  }

  // Transfer endpoints
  @GetMapping("/{accountId}/transfers")
  public ResponseEntity<String> findAllTransfersByAccountId(@PathVariable String accountId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body("Retrieving all transfers for account " + accountId + " is not implemented yet.");
  }

  @GetMapping("/{accountId}/transfers/{transferId}")
  public ResponseEntity<String> findTransferById(
      @PathVariable String accountId, @PathVariable String transferId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body(
            "Retrieving transfer "
                + transferId
                + " for account "
                + accountId
                + " is not implemented yet.");
  }

  @PostMapping("/{accountId}/transfers")
  public ResponseEntity<String> saveTransfer(
      @PathVariable String accountId, @RequestBody Transfer transfer) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body("Saving a new transfer for account " + accountId + " is not implemented yet.");
  }

  @PutMapping("/{accountId}/transfers/{transferId}")
  public ResponseEntity<String> updateTransfer(
      @PathVariable String accountId, @PathVariable String transferId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body(
            "Updating transfer "
                + transferId
                + " for account "
                + accountId
                + " is not implemented yet.");
  }

  @DeleteMapping("/{accountId}/transfers/{transferId}")
  public ResponseEntity<String> deleteTransfer(
      @PathVariable String accountId, @PathVariable String transferId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body(
            "Deleting transfer "
                + transferId
                + " for account "
                + accountId
                + " is not implemented yet.");
  }

  @PostMapping("/{accountId}/statement")
  public ResponseEntity<String> generateStatement(
      @PathVariable String accountId, @RequestBody Statement statement) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body(
            "Generating statement information for account "
                + accountId
                + " is not implemented yet.");
  }

  // Overdraft endpoints
  @GetMapping("/{accountId}/overdraft")
  public ResponseEntity<String> findOverdraftByAccountId(@PathVariable String accountId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body(
            "Retrieving overdraft information for account "
                + accountId
                + " is not implemented yet.");
  }

  @PostMapping("/{accountId}/overdraft")
  public ResponseEntity<String> saveOverdraft(
      @PathVariable String accountId, @RequestBody Overdraft overdraft) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body("Saving overdraft information for account " + accountId + " is not implemented yet.");
  }

  @PutMapping("/{accountId}/overdraft")
  public ResponseEntity<String> updateOverdraft(@PathVariable String accountId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body(
            "Updating overdraft information for account " + accountId + " is not implemented yet.");
  }

  @DeleteMapping("/{accountId}/overdraft")
  public ResponseEntity<String> deleteOverdraft(@PathVariable String accountId) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
        .body(
            "Deleting overdraft information for account " + accountId + " is not implemented yet.");
  }
}
