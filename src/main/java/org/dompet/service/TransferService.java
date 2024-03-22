package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Transfer;
import org.dompet.repository.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
  public final TransferRepository transferRepository;

  public TransferService(TransferRepository transferRepository) {
    this.transferRepository = transferRepository;
  }

  public Transfer save(Transfer transfer) {
    return transferRepository.save(transfer);
  }

  public Optional<Transfer> findById(String id) {
    return transferRepository.findById(id);
  }

  public List<Transfer> findAll() {
    return transferRepository.findAll();
  }

  public void deleteById(String id) {
    transferRepository.deleteById(id);
  }
}
