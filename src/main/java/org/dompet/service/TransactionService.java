package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Transaction;
import org.dompet.repository.TransactionRepository;
import org.dompet.utils.EntityUtil;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  public final TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public Transaction saveTransaction(Transaction transaction) {
    if (transactionRepository.getById(transaction.getTransactionId()).isEmpty()) {
      return transactionRepository.save(transaction);
    }
    Transaction existingTransaction =
        transactionRepository
            .getById(transaction.getTransactionId())
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
    EntityUtil.updateEntityFields(existingTransaction, transaction);
    return transactionRepository.save(existingTransaction);
  }

  public Optional<Transaction> findTransactionById(String id) {
    return transactionRepository.getById(id);
  }

  public List<Transaction> findAllTransactions() {
    return transactionRepository.getAll();
  }

  public void deleteTransactionById(String id) {
    transactionRepository.deleteById(id);
  }
}
