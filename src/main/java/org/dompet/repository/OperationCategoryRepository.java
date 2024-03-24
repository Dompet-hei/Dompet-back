package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.OperationCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationCategoryRepository {
  OperationCategory save(OperationCategory operationCategory);

  Optional<OperationCategory> findById(String id);

  List<OperationCategory> findAll();

  void deleteById(String id);
}
