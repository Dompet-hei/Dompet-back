package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Transaction;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository extends CRUDOperationImpl<Transaction> {
  public TransactionRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<Transaction> getActualClass() {
    return Transaction.class;
  }

  public List<Transaction> findAllByAccountId(String accountId) {
    return null;
  }

  public Transaction findByAccountIdAndTransactionId(String accountId, String transactionId) {
    return null;
  }
}
