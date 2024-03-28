package org.dompet.service;

import java.util.List;

import org.dompet.model.Overdraft;
import org.dompet.repository.OverdraftRepository;
import org.springframework.stereotype.Service;

@Service
public class OverdraftService {
  private final OverdraftRepository overdraftRepository= new OverdraftRepository();

  public void createBank(Overdraft overdraft) {
    overdraftRepository.insert(overdraft, true);
  }

  public Overdraft getOverdraftById(Integer id) {
    return overdraftRepository.getById(id);
  }

  public List<Overdraft> getAllOverdrafts() {
    return overdraftRepository.getAll();
  }

  public void updateOverdraft(Overdraft overdraft) {
    overdraftRepository.save(overdraft);
  }

  public void deleteOverdraftById(Integer id) {
    overdraftRepository.deleteById(id);
  }
}
