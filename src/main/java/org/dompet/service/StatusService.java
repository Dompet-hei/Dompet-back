package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Status;
import org.dompet.repository.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
  public final StatusRepository statusRepository;

  public StatusService(StatusRepository statusRepository) {
    this.statusRepository = statusRepository;
  }

  public Status save(Status status) {
    return statusRepository.save(status);
  }

  public Optional<Status> findById(String id) {
    return statusRepository.findById(id);
  }

  public List<Status> findAll() {
    return statusRepository.findAll();
  }

  public void deleteById(String id) {
    statusRepository.deleteById(id);
  }
}
