package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.Bank;
import org.dompet.service.BankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankController {
  private final BankService bankService;

  @GetMapping
  public List<Bank> findAllBanks() {
    return bankService.findAllBanks();
  }

  @GetMapping("/{id}")
  public Optional<Bank> findBankById(@PathVariable String id) {
    return bankService.findBankById(id);
  }
}
