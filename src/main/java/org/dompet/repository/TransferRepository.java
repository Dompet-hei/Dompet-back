package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Transfer;

public class TransferRepository extends CRUDOperationImpl<Transfer> {
    @Override
    protected Class<Transfer> getActualClass() {
        return Transfer.class;
    }
}
