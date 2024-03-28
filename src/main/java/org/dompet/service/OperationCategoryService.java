package org.dompet.service;

import java.util.List;

import org.dompet.model.OperationCategory;
import org.dompet.repository.OperationCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationCategoryService {
  private final OperationCategoryRepository operationCategoryRepository = new OperationCategoryRepository(null);

  public OperationCategory findById(String id) {
    return operationCategoryRepository.getById(Integer.valueOf(id));
  }

  public List<OperationCategory> findAll() {
    return operationCategoryRepository.getAll();
  }

  public OperationCategory save(OperationCategory operationCategory) {
    operationCategoryRepository.save(operationCategory);
    return operationCategory;
  }

  public void deleteById(String id) {
    operationCategoryRepository.deleteById(Integer.valueOf(id));
  }
}
