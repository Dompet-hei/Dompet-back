package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Overdraft;
import org.dompet.repository.OverdraftRepository;
import org.springframework.stereotype.Service;

@Service
public class OverdraftService {
  public final OverdraftRepository overdraftRepository;

  public OverdraftService(OverdraftRepository overdraftRepository) {
    this.overdraftRepository = overdraftRepository;
  }

  public Overdraft save(Overdraft overdraft) {
    return overdraftRepository.save(overdraft);
  }

  public Optional<Overdraft> findById(String id) {
    return overdraftRepository.findById(id);
  }

  public List<Overdraft> findAll() {
    return overdraftRepository.findAll();
  }

  public void deleteById(String id) {
    overdraftRepository.deleteById(id);
  }
}
