package org.dompet.service;

import org.dompet.model.Bank;
import org.dompet.repository.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
  private final BankRepository bankRepository= new BankRepository(null);

  public Bank findById(String id) {
    return bankRepository.getById(Integer.valueOf(id));
  }

  public List<Bank> findAll() {
    return bankRepository.getAll();
  }

  public Bank save(Bank bank) {
    bankRepository.save(bank);
    return bank;
  }

  public void deleteById(Integer id) {
    bankRepository.deleteById(id);
  }
}
