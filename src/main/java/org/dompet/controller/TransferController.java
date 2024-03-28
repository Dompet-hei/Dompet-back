package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.Transfer;
import org.dompet.service.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/transfer")
public class TransferController {
  private final TransferService transferService;

  @GetMapping
  public List<Transfer> findAllTransfers() {
    return transferService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Transfer> findTransferById(@PathVariable String id) {
    return Optional.ofNullable(transferService.findById(id));
  }

  @PutMapping
  public Transfer saveTransfer(@RequestBody Transfer transfer) {
    return transferService.save(transfer);
  }

  @DeleteMapping("/{id}")
  public void deleteTransferById(@PathVariable String id) {
    transferService.deleteById(id);
  }
}
