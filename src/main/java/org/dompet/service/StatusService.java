package org.dompet.service;

import java.util.List;

import org.dompet.model.Status;
import org.dompet.repository.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
  private final StatusRepository statusRepository= new StatusRepository(null);

  public Status findById(String id) {
    return statusRepository.getById(Integer.valueOf(id));
  }

  public List<Status> findAll() {
    return statusRepository.getAll();
  }

  public Status save(Status status) {
    statusRepository.save(status);
    return status;
  }

  public void deleteById(String id) {
    statusRepository.deleteById(Integer.valueOf(id));
  }
}
