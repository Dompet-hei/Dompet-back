package org.dompet.service;

import java.util.List;

import org.dompet.model.Transfer;
import org.dompet.repository.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
  private final TransferRepository transferRepository= new TransferRepository();

  public void createTransfer(Transfer transfer) {
    transferRepository.insert(transfer, true);
  }

  public Transfer getTransferById(Integer id) {
    return transferRepository.getById(id);
  }

  public List<Transfer> getAllTransfers() {
    return transferRepository.getAll();
  }

  public void updateTransfer(Transfer transfer) {
    transferRepository.save(transfer);
  }

  public void deleteTransferById(Integer id) {
    transferRepository.deleteById(id);
  }
}
