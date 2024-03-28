package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Bank;
import org.dompet.utils.database.DBConnector;

public class BankRepository extends CRUDOperationImpl<Bank> {

    public BankRepository(DBConnector bank) {
        super(bank);
    }

    @Override
    protected Class<Bank> getActualClass() {
        return Bank.class;
    }
}
