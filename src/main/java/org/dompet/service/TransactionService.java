package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Transaction;
import org.dompet.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  public final TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public Transaction save(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  public Optional<Transaction> findById(String id) {
    return transactionRepository.findById(id);
  }

  public List<Transaction> findAll() {
    return transactionRepository.findAll();
  }

  public void deleteById(String id) {
    transactionRepository.deleteById(id);
  }
}
