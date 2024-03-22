package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Bank;
import org.dompet.repository.BankRepository;
import org.springframework.stereotype.Service;

@Service
public class BankService {
  public final BankRepository bankRepository;

  public BankService(BankRepository BankRepository) {
    this.bankRepository = BankRepository;
  }

  public Bank save(Bank Bank) {
    return bankRepository.save(Bank);
  }

  public Optional<Bank> findById(String id) {
    return bankRepository.findById(id);
  }

  public List<Bank> findAll() {
    return bankRepository.findAll();
  }

  public void deleteById(String id) {
    bankRepository.deleteById(id);
  }
}
