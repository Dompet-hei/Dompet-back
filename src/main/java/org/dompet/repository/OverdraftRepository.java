package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Overdraft;
import org.dompet.utils.database.DBConnector;

public class OverdraftRepository extends CRUDOperationImpl<Overdraft> {
    public OverdraftRepository(DBConnector bank) {
        super(bank);
    }
    @Override
    protected Class<Overdraft> getActualClass() {
        return Overdraft.class;
    }
}
