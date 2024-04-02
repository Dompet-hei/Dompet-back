package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.TransferRecipient;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRecipientRepository extends CRUDOperationImpl<TransferRecipient>{
    public TransferRecipientRepository(DBConnector transferRecipient) {
        super(transferRecipient);
    }
    @Override
    protected Class<TransferRecipient> getActualClass() {
        return TransferRecipient.class;
    }
  TransferRecipient save(TransferRecipient transferRecipient);

  Optional<TransferRecipient> findById(Long id);

  List<TransferRecipient> findAll();

  void deleteById(Long id);

  List<TransferRecipient> findTransferRecipientByTransferId(String transferId);
}
