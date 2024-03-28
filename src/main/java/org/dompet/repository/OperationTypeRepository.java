package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.OperationType;

public class OperationTypeRepository extends CRUDOperationImpl<OperationType> {
    @Override
    protected Class<OperationType> getActualClass() {
        return OperationType.class;
    }
}
