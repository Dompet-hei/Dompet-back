package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.Overdraft;
import org.dompet.service.OverdraftService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/overdraft")
public class OverdraftController {
  private final OverdraftService overdraftService;

  @GetMapping
  public List<Overdraft> findAllOverdraft() {
    return overdraftService.findAllOverdrafts();
  }

  @GetMapping("/{id}")
  public Optional<Overdraft> findOverdraftById(@PathVariable String id) {
    return overdraftService.findOverdraftById(id);
  }

  @PutMapping
  public Overdraft saveOverdraft(@RequestBody Overdraft overdraft) {
    return overdraftService.saveOverdraft(overdraft);
  }

  @DeleteMapping("/{id}")
  public void deleteOverdraftById(@PathVariable String id) {
    overdraftService.deleteOverdraftById(id);
  }
}
