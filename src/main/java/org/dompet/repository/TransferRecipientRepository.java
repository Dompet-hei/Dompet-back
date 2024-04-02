package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Transfer;
import org.dompet.model.TransferRecipient;
import org.dompet.utils.annotations.IdAnnotation;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class TransferRecipientRepository extends CRUDOperationImpl<TransferRecipient> {
  public TransferRecipientRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<TransferRecipient> getActualClass() {
    return TransferRecipient.class;
  }

  public List<TransferRecipient> findTransferRecipientByTransferId(String transferId) {
    List<TransferRecipient> transferRecipients =
        getAllWithCondition(
            IdAnnotation.getIdColumnName(Transfer.class) + " = ?", null, null, transferId);
    return transferRecipients.isEmpty() ? null : transferRecipients;
  }
}
