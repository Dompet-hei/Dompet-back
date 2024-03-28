package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.TransferRecipient;

public class TransferRecipientRepository extends CRUDOperationImpl<TransferRecipient> {
    @Override
    protected Class<TransferRecipient> getActualClass() {
        return TransferRecipient.class;
    }
}
