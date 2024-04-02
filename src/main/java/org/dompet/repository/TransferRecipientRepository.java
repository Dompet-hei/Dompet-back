package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.TransferRecipient;
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
    return null;
  }
}
