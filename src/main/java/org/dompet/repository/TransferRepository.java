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
    List<Transfer> transfers =
        getAllWithCondition("sender_account_id = ?", null, null, senderAccountId);
    return transfers.isEmpty() ? null : transfers;
  }

  public Transfer findBySenderAccountIdAndTransferId(String senderAccountId, String transferId) {
    List<Transfer> transfers =
        getAllWithCondition(
            "sender_account_id = ? AND transfer_id = ?", null, null, senderAccountId, transferId);
    return transfers.isEmpty() ? null : transfers.get(0);
  }
}
