package org.dompet.service;

import java.util.List;

import org.dompet.model.TransferRecipient;
import org.dompet.repository.TransferRecipientRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferRecipientService {
  private final TransferRecipientRepository transfertRecipientRepository= new TransferRecipientRepository();

  public void createTransferRecipient(TransferRecipient transfertRecipient) {
    transfertRecipientRepository.insert(transfertRecipient, true);
  }

  public TransferRecipient getTransferRecipientById(Integer id) {
    return transfertRecipientRepository.getById(id);
  }

  public List<TransferRecipient> getAllTransferRecipients() {
    return transfertRecipientRepository.getAll();
  }

  public void updateTransferRecipient(TransferRecipient transfertRecipient) {
    transfertRecipientRepository.save(transfertRecipient);
  }

  public void deleteTransferRecipientById(Integer id) {
    transfertRecipientRepository.deleteById(id);
  }
}
