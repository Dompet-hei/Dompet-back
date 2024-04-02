package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import java.util.List;
import java.util.Optional;
import org.dompet.utils.database.DBConnector;
import org.dompet.model.OperationCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationCategoryRepository extends CRUDOperationImpl<OperationCategory>{
    public OperationCategoryRepository(DBConnector dbConnector) {
        super(dbConnector);
    }
    OperationCategory save(OperationCategory operationCategory);

  Optional<OperationCategory> findById(String id);

  List<OperationCategory> findAll();

  void deleteById(String id);

  List<OperationCategory> findAllByOperationTypeIs(String operationType);
@Override
protected Class<OperationCategory> getActualClass() {
    return OperationCategory.class;
}
}
