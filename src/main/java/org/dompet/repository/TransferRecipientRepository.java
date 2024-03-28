package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.TransferRecipient;
import org.dompet.utils.database.DBConnector;

public class TransferRecipientRepository extends CRUDOperationImpl<TransferRecipient> {
    public TransferRecipientRepository(DBConnector transferRecipient) {
        super(transferRecipient);
    }
    @Override
    protected Class<TransferRecipient> getActualClass() {
        return TransferRecipient.class;
    }
}
