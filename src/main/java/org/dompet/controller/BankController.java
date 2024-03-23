package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.Bank;
import org.dompet.service.BankService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/bank")
public class BankController {
  private final BankService bankService;

  @GetMapping
  public List<Bank> findAllBanks() {
    return bankService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Bank> findBankById(@PathVariable String id) {
    return bankService.findById(id);
  }

  @PutMapping
  public Bank saveBank(@RequestBody Bank bank) {
    return bankService.save(bank);
  }

  @DeleteMapping("/{id}")
  public void deleteBankById(@PathVariable String id) {
    bankService.deleteById(id);
  }
}
