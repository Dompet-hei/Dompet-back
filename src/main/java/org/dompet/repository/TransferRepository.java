package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Transfer;
import org.dompet.utils.database.DBConnector;

public class TransferRepository extends CRUDOperationImpl<Transfer> {
    public TransferRepository(DBConnector transferRepository) {
        super(transferRepository);
    }
    @Override
    protected Class<Transfer> getActualClass() {
        return Transfer.class;
    }
}
