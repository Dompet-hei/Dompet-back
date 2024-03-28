package org.dompet.service;

import java.util.List;

import org.dompet.model.Status;
import org.dompet.repository.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
  private final StatusRepository statusRepository= new StatusRepository();

  public void createStatus(Status status) {
    statusRepository.insert(status, true);
  }

  public Status getStatusById(Integer id) {
    return statusRepository.getById(id);
  }

  public List<Status> getAllStatuses() {
    return statusRepository.getAll();
  }

  public void updateStatus(Status status) {
    statusRepository.save(status);
  }

  public void deleteStatusById(Integer id) {
    statusRepository.deleteById(id);
  }
}
