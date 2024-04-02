package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Bank;
import org.dompet.repository.BankRepository;
import org.springframework.stereotype.Service;

@Service
public class BankService {
  public final BankRepository bankRepository;

  public BankService(BankRepository bankRepository) {
    this.bankRepository = bankRepository;
  }

  public Optional<Bank> findBankById(String id) {
    return bankRepository.getById(id);
  }

  public List<Bank> findAllBanks() {
    return bankRepository.getAll();
  }
}
