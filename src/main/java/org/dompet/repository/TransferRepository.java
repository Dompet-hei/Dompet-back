package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Transfer;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class TransferRepository extends CRUDOperationImpl<Transfer> {
  public TransferRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<Transfer> getActualClass() {
    return Transfer.class;
  }

  public List<Transfer> findAllBySenderAccountId(String senderAccountId) {
    return null;
  }

  public Transfer findBySenderAccountIdAndTransferId(String SenderAccountId, String transferId) {
    return null;
  }
}
