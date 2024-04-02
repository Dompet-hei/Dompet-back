package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Transfer;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends CRUDOperationImpl<Transfer>{
  Transfer save(Transfer transfer);
    public TransferRepository(DBConnector transferRepository) {
        super(transferRepository);
    }
    @Override
    protected Class<Transfer> getActualClass() {
        return Transfer.class;
    }

  Optional<Transfer> findById(String id);

  List<Transfer> findAll();

  void deleteById(String id);

  List<Transfer> findAllBySenderAccountId(String senderAccountId);

  Transfer findBySenderAccountIdAndTransferId(String SenderAccountId, String transferId);
}
