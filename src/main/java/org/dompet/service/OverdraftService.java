package org.dompet.service;

import java.util.List;

import org.dompet.model.Overdraft;
import org.dompet.repository.OverdraftRepository;
import org.springframework.stereotype.Service;

@Service
public class OverdraftService {
  private final OverdraftRepository overdraftRepository= new OverdraftRepository(null);

  public Overdraft findById(String id) {
    return overdraftRepository.getById(Integer.valueOf(id));
  }

  public List<Overdraft> findAll() {
    return overdraftRepository.getAll();
  }

  public Overdraft save(Overdraft overdraft) {
    overdraftRepository.save(overdraft);
    return overdraft;
  }

  public void deleteById(String id) {
    overdraftRepository.deleteById(Integer.valueOf(id));
  }
}
