package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Transaction;
import org.dompet.utils.database.DBConnector;

public class TransactionRepository extends CRUDOperationImpl<Transaction> {
    public TransactionRepository(DBConnector transaction) {
        super(transaction);
    }
    @Override
    protected Class<Transaction> getActualClass() {
        return Transaction.class;
    }
}
