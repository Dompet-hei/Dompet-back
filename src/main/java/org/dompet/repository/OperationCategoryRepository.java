package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.OperationCategory;

public class OperationCategoryRepository extends CRUDOperationImpl<OperationCategory> {
    @Override
    protected Class<OperationCategory> getActualClass() {
        return OperationCategory.class;
    }
}
