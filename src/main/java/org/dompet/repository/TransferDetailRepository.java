package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.TransferDetail;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class TransferDetailRepository extends CRUDOperationImpl<TransferDetail> {
  public TransferDetailRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<TransferDetail> getActualClass() {
    return TransferDetail.class;
  }

  List<TransferDetail> findAll() {
    return null;
  }

  List<TransferDetail> findAllByAccountId(String accountId) {
    return null;
  }
}
