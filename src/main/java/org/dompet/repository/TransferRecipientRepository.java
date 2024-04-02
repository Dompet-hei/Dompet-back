package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.TransferRecipient;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRecipientRepository {
  TransferRecipient save(TransferRecipient transferRecipient);

  Optional<TransferRecipient> findById(Long id);

  List<TransferRecipient> findAll();

  void deleteById(Long id);

  List<TransferRecipient> findTransferRecipientByTransferId(String transferId);
}
