package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.OperationType;
import org.dompet.utils.database.DBConnector;

public class OperationTypeRepository extends CRUDOperationImpl<OperationType> {

    public OperationTypeRepository(DBConnector operationType) {
        super(operationType);
    }
    @Override
    protected Class<OperationType> getActualClass() {
        return OperationType.class;
    }
}
