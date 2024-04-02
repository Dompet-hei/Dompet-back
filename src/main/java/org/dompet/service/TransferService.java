package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Transfer;
import org.dompet.model.TransferDetail;
import org.dompet.model.TransferRecipient;
import org.dompet.repository.TransferDetailRepository;
import org.dompet.repository.TransferRecipientRepository;
import org.dompet.repository.TransferRepository;
import org.dompet.utils.EntityUtil;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
  public final TransferRepository transferRepository;
  public final TransferDetailRepository transferDetailRepository;
  public final TransferRecipientRepository transferRecipientRepository;

  public TransferService(
      TransferRepository transferRepository,
      TransferDetailRepository transferDetailRepository,
      TransferRecipientRepository transferRecipientRepository) {
    this.transferRepository = transferRepository;
    this.transferDetailRepository = transferDetailRepository;
    this.transferRecipientRepository = transferRecipientRepository;
  }

  public List<TransferDetail> findAllDetails() {
    return transferDetailRepository.getAll();
  }

  public Transfer saveTransfer(Transfer transfer) {
    if (transferRecipientRepository.getById(transfer.getTransferId()).isEmpty()) {
      return transferRepository.insert(transfer, true);
    }
    Transfer existingTransfer =
        transferRepository
            .getById(transfer.getTransferId())
            .orElseThrow(() -> new RuntimeException("Transfer not found"));
    EntityUtil.updateEntityFields(existingTransfer, transfer);
    return transferRepository.insert(existingTransfer, true);
  }

  public Optional<Transfer> findTransferById(String id) {
    return transferRepository.getById(id);
  }

  public List<Transfer> findAllTransfers() {
    return transferRepository.getAll();
  }

  public void deleteTransferById(String id) {
    transferRepository.deleteById(id);
  }

  public TransferRecipient saveTransferRecipient(TransferRecipient transferRecipient) {
    if (transferRecipientRepository.getById(transferRecipient.getTransferRecipientId()).isEmpty()) {
      return transferRecipientRepository.insert(transferRecipient, true);
    }
    TransferRecipient existingTransferRecipient =
        transferRecipientRepository
            .getById(transferRecipient.getTransferRecipientId())
            .orElseThrow(() -> new RuntimeException("TransferRecipient not found"));
    EntityUtil.updateEntityFields(existingTransferRecipient, transferRecipient);
    return transferRecipientRepository.insert(existingTransferRecipient, true);
  }

  public Optional<TransferRecipient> findTransferRecipientById(Long id) {
    return transferRecipientRepository.getById(id);
  }

  public List<TransferRecipient> findAllTransferRecipients() {
    return transferRecipientRepository.getAll();
  }

  public List<TransferRecipient> findTransferRecipientByTransferId(String transferId) {
    return transferRecipientRepository.findTransferRecipientByTransferId(transferId);
  }

  public void deleteTransferRecipientById(Long id) {
    transferRecipientRepository.deleteById(id);
  }
}
