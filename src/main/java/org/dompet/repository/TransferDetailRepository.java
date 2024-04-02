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

  List<TransferDetail> findAllByAccountId(String accountId) {
    List<TransferDetail> transferDetail =
        getAllWithCondition("account_id = ?", null, null, accountId);
    return transferDetail.isEmpty() ? null : transferDetail;
  }
}
