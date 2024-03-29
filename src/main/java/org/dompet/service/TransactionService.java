package org.dompet.service;

import java.util.List;

import org.dompet.model.Transaction;
import org.dompet.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  private final TransactionRepository transactionRepository= new TransactionRepository(null);

  public Transaction findById(String id) {
    return transactionRepository.getById(id);
  }

  public List<Transaction> findAll() {
    return transactionRepository.getAll();
  }

  public Transaction save(Transaction transaction) {
    transactionRepository.save(transaction);
    return transaction;
  }

  public void deleteById(String id) {
    transactionRepository.deleteById(id);
  }
}
