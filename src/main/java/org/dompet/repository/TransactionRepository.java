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
    List<Transaction> transactions = getAllWithCondition("account_id = ?", null, null, accountId);
    return transactions.isEmpty() ? null : transactions;
  }

  public Transaction findByAccountIdAndTransactionId(String accountId, String transactionId) {
    List<Transaction> transactions =
        getAllWithCondition(
            "account_id = ? AND transaction_id = ?", null, null, accountId, transactionId);
    return transactions.isEmpty() ? null : transactions.get(0);
  }
}
