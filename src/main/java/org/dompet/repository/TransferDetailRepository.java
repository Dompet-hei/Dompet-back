package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Transfer;
import org.dompet.model.TransferDetail;
import org.dompet.utils.annotations.IdAnnotation;
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
        getAllWithCondition(IdAnnotation.getIdColumnName(Transfer.class) + " = ?", null, null, transferId);
    return transferDetails.isEmpty() ? null : transferDetails;
  }
}
