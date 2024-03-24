package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.TransferRecipient;
import org.dompet.model.TransferRecipientId;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRecipientRepository extends BaseRepository<TransferRecipient>{
  TransferRecipient save(TransferRecipient transferRecipient);

  Optional<TransferRecipient> findById(TransferRecipientId id);

  List<TransferRecipient> findAll();

  void deleteById(TransferRecipientId id);
}
