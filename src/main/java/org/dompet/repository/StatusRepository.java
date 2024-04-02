package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Status;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class StatusRepository extends CRUDOperationImpl<Status> {
  public StatusRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<Status> getActualClass() {
    return Status.class;
  }
}
