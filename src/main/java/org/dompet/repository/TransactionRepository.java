package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Transaction;

public class TransactionRepository extends CRUDOperationImpl<Transaction> {
    @Override
    protected Class<Transaction> getActualClass() {
        return Transaction.class;
    }
}
