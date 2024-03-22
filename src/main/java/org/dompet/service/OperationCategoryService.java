package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.OperationCategory;
import org.dompet.repository.OperationCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationCategoryService {
  public final OperationCategoryRepository operationCategoryRepository;

  public OperationCategoryService(OperationCategoryRepository operationCategoryRepository) {
    this.operationCategoryRepository = operationCategoryRepository;
  }

  public OperationCategory save(OperationCategory operationCategory) {
    return operationCategoryRepository.save(operationCategory);
  }

  public Optional<OperationCategory> findById(String id) {
    return operationCategoryRepository.findById(id);
  }

  public List<OperationCategory> findAll() {
    return operationCategoryRepository.findAll();
  }

  public void deleteById(String id) {
    operationCategoryRepository.deleteById(id);
  }
}
