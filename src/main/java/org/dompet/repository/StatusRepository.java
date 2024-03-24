package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Status;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository {
  Status save(Status status);

  Optional<Status> findById(String id);

  List<Status> findAll();

  void deleteById(String id);
}
