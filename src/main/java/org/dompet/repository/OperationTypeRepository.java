package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.OperationType;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository {
  OperationType save(OperationType operationType);

  Optional<OperationType> findById(String id);

  List<OperationType> findAll();

  void deleteById(String id);
}
