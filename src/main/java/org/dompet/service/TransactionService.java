package org.dompet.service;

import java.util.List;
import java.util.Optional;

import org.dompet.model.Transaction;
import org.dompet.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  private final TransactionRepository transactionRepository= new TransactionRepository();

  public void createTransaction(Transaction transaction) {
    transactionRepository.insert(transaction, true);
  }

  public Transaction getTransactionById(Integer id) {
    return transactionRepository.getById(id);
  }

  public List<Transaction> getAllTransactions() {
    return transactionRepository.getAll();
  }

  public void updateTransaction(Transaction transaction) {
    transactionRepository.save(transaction);
  }

  public void deleteTransactionById(Integer id) {
    transactionRepository.deleteById(id);
  }
}
