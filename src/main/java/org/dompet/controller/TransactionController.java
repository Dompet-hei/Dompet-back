package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.Transaction;
import org.dompet.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionController {
  private final TransactionService transactionService;

  @GetMapping
  public List<Transaction> findAllTransactions() {
    return transactionService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Transaction> findTransactionById(@PathVariable String id) {
    return transactionService.findById(id);
  }

  @PutMapping
  public Transaction saveTransaction(@RequestBody Transaction transaction) {
    return transactionService.save(transaction);
  }

  @DeleteMapping("/{id}")
  public void deleteTransactionById(@PathVariable String id) {
    transactionService.deleteById(id);
  }
}
