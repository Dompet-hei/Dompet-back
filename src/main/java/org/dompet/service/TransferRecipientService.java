package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.TransferRecipient;
import org.dompet.model.TransferRecipientId;
import org.dompet.repository.TransferRecipientRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferRecipientService {
  public final TransferRecipientRepository transferRecipientRepository;

  public TransferRecipientService(TransferRecipientRepository transferRecipientRepository) {
    this.transferRecipientRepository = transferRecipientRepository;
  }

  public TransferRecipient save(TransferRecipient transferRecipient) {
    return transferRecipientRepository.save(transferRecipient);
  }

  public Optional<TransferRecipient> findById(TransferRecipientId id) {
    return transferRecipientRepository.findById(id);
  }

  public List<TransferRecipient> findAll() {
    return transferRecipientRepository.findAll();
  }

  public void deleteById(TransferRecipientId id) {
    transferRecipientRepository.deleteById(id);
  }
}
