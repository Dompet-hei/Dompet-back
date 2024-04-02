package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.OperationCategory;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class OperationCategoryRepository extends CRUDOperationImpl<OperationCategory> {
  public OperationCategoryRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<OperationCategory> getActualClass() {
    return OperationCategory.class;
  }

  public List<OperationCategory> findAllByOperationTypeIs(String operationType) {
    return null;
  }
}
