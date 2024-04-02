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

  public List<TransferDetail> findTransferDetails(String transferId) {
    List<TransferDetail> transferDetails =
        getAllWithCondition("transfer_id = ?", null, null, transferId);
    return transferDetails.isEmpty() ? null : transferDetails;
  }
}
