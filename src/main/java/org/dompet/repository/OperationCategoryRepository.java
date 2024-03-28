package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.OperationCategory;
import org.dompet.utils.database.DBConnector;

public class OperationCategoryRepository extends CRUDOperationImpl<OperationCategory> {
    public OperationCategoryRepository(DBConnector dbConnector) {
        super(dbConnector);
    }

    @Override
    protected Class<OperationCategory> getActualClass() {
        return OperationCategory.class;
    }
}
