package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository {
  Transaction save(Transaction transaction);

  Optional<Transaction> findById(String id);

  List<Transaction> findAll();

  void deleteById(String id);
}
