package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.TransferRecipient;
import org.dompet.service.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transferRecipient")
public class TransferRecipientController {
  private final TransferService transferService;

  @GetMapping
  public List<TransferRecipient> findAllTransferRecipient() {
    return transferService.findAllTransferRecipients();
  }

  @GetMapping("/{id}")
  public Optional<TransferRecipient> findByIdTransferRecipient(@PathVariable Long id) {
    return transferService.findTransferRecipientById(id);
  }

  @PutMapping
  public TransferRecipient saveTransferRecipient(@RequestBody TransferRecipient transferRecipient) {
    return transferService.saveTransferRecipient(transferRecipient);
  }

  @DeleteMapping("/{id}")
  public void deleteTransferRecipientById(@PathVariable Long id) {
    transferService.deleteTransferRecipientById(id);
  }
}
