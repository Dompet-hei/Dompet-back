package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.Transaction;
import org.dompet.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
  private final TransactionService transactionService;

  @GetMapping
  public List<Transaction> findAllTransactions() {
    return transactionService.findAllTransactions();
  }

  @GetMapping("/{id}")
  public Optional<Transaction> findTransactionById(@PathVariable String id) {
    return transactionService.findTransactionById(id);
  }

  @PutMapping
  public Transaction saveTransaction(@RequestBody Transaction transaction) {
    return transactionService.saveTransaction(transaction);
  }

  @DeleteMapping("/{id}")
  public void deleteTransactionById(@PathVariable String id) {
    transactionService.deleteTransactionById(id);
  }
}
