package org.dompet.service;

import java.util.List;

import org.dompet.model.TransferRecipient;
import org.dompet.repository.TransferRecipientRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferRecipientService {
  private final TransferRecipientRepository transferRecipientRepository= new TransferRecipientRepository(null);

  public TransferRecipient findById(String id) {
    return transferRecipientRepository.getById(Integer.valueOf(id));
  }

  public List<TransferRecipient> findAll() {
    return transferRecipientRepository.getAll();
  }

  public TransferRecipient save(TransferRecipient transferRecipient) {
    transferRecipientRepository.save(transferRecipient);
    return transferRecipient;
  }

  public void deleteById(String id) {
    transferRecipientRepository.deleteById(Integer.valueOf(id));
  }
}
