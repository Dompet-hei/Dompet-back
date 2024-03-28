package org.dompet.service;

import java.util.List;

import org.dompet.model.OperationCategory;
import org.dompet.repository.OperationCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationCategoryService {
  private final OperationCategoryRepository operationCategoryRepository = new OperationCategoryRepository();

  public void createOperationCategory(OperationCategory operationCategory) {
    operationCategoryRepository.insert(operationCategory, true);
  }

  public OperationCategory getOperationCategoryById(Integer id) {
    return operationCategoryRepository.getById(id);
  }

  public List<OperationCategory> getAllOperationCategory() {
    return operationCategoryRepository.getAll();
  }

  public void updateOperationCategory(OperationCategory operationCategory) {
    operationCategoryRepository.save(operationCategory);
  }

  public void deleteOperationCategoryById(Integer id) {
    operationCategoryRepository.deleteById(id);
  }
}
