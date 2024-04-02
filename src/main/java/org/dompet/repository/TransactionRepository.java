package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Transaction;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CRUDOperationImpl<Transaction> {
    public TransactionRepository(DBConnector transaction) {
        super(transaction);
    }
    @Override
    protected Class<Transaction> getActualClass() {
        return Transaction.class;
    }
  Transaction save(Transaction transaction);

  Optional<Transaction> findById(String id);

  List<Transaction> findAll();

  void deleteById(String id);

  List<Transaction> findAllByAccountId(String accountId);

  Transaction findByAccountIdAndTransactionId(String accountId, String transactionId);
}
