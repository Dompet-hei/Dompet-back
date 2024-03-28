package org.dompet.service;

import org.dompet.model.Bank;
import org.dompet.repository.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
  private final BankRepository bankRepository= new BankRepository();

  public void createBank(Bank bank) {
    bankRepository.insert(bank, true);
  }

  public Bank getBankById(Integer id) {
    return bankRepository.getById(id);
  }

  public List<Bank> getAllBanks() {
    return bankRepository.getAll();
  }

  public void updateBank(Bank bank) {
    bankRepository.save(bank);
  }

  public void deleteBankById(Integer id) {
    bankRepository.deleteById(id);
  }
}
