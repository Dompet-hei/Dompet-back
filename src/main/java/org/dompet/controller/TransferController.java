package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.Transfer;
import org.dompet.model.TransferRecipient;
import org.dompet.service.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {
  private final TransferService transferService;

  @GetMapping
  public List<Transfer> findAllTransfers() {
    return transferService.findAllTransfers();
  }

  @GetMapping("/{id}/transferRecipient")
  public List<TransferRecipient> findAllTransferRecipientByTransferId(@PathVariable String id) {
    return transferService.findTransferRecipientByTransferId(id);
  }

  @GetMapping("/{id}")
  public Optional<Transfer> findTransferById(@PathVariable String id) {
    return transferService.findTransferById(id);
  }

  @PutMapping
  public Transfer saveTransfer(@RequestBody Transfer transfer) {
    return transferService.saveTransfer(transfer);
  }

  @DeleteMapping("/{id}")
  public void deleteTransferById(@PathVariable String id) {
    transferService.deleteTransferById(id);
  }
}
