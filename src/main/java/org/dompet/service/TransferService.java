package org.dompet.service;

import java.util.List;

import org.dompet.model.Transfer;
import org.dompet.repository.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
  private final TransferRepository transferRepository= new TransferRepository(null);

  public Transfer findById(String id) {
    return transferRepository.getById(Integer.valueOf(id));
  }

  public List<Transfer> findAll() {
    return transferRepository.getAll();
  }

  public Transfer save(Transfer transfer) {
    transferRepository.save(transfer);
    return transfer;
  }

  public void deleteById(String id) {
    transferRepository.deleteById(Integer.valueOf(id));
  }
}
