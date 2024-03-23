package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.TransferRecipient;
import org.dompet.model.TransferRecipientId;
import org.dompet.service.TransferRecipientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/transferRecipient")
public class TransferRecipientController {
  private final TransferRecipientService transferRecipientService;

  @GetMapping
  public List<TransferRecipient> findAllTransferRecipient() {
    return transferRecipientService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<TransferRecipient> findByIdTransferRecipient(
      @PathVariable TransferRecipientId id) {
    return transferRecipientService.findById(id);
  }

  @PutMapping
  public TransferRecipient saveTransferRecipient(@RequestBody TransferRecipient transferRecipient) {
    return transferRecipientService.save(transferRecipient);
  }

  @DeleteMapping("/{id}")
  public void deleteTransferRecipientById(@PathVariable TransferRecipientId id) {
    transferRecipientService.deleteById(id);
  }
}
