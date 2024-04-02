package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Overdraft;
import org.dompet.repository.OverdraftRepository;
import org.dompet.utils.EntityUtil;
import org.springframework.stereotype.Service;

@Service
public class OverdraftService {
  public final OverdraftRepository overdraftRepository;

  public OverdraftService(OverdraftRepository overdraftRepository) {
    this.overdraftRepository = overdraftRepository;
  }

  public Overdraft saveOverdraft(Overdraft overdraft) {
    if (overdraftRepository.findById(overdraft.getOverdraftId()).isEmpty()) {
      return overdraftRepository.save(overdraft);
    }
    Overdraft existingOverdraft =
        overdraftRepository
            .findById(overdraft.getOverdraftId())
            .orElseThrow(() -> new RuntimeException("Overdraft not found"));
    EntityUtil.updateEntityFields(existingOverdraft, overdraft);
    return overdraftRepository.save(existingOverdraft);
  }

  public Optional<Overdraft> findOverdraftById(String id) {
    return overdraftRepository.findById(id);
  }

  public List<Overdraft> findAllOverdrafts() {
    return overdraftRepository.findAll();
  }

  public void deleteOverdraftById(String id) {
    overdraftRepository.deleteById(id);
  }
}
