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

  public Optional<OperationCategory> findOperationCategoryById(String id) {
    return operationCategoryRepository.findById(id);
  }

  public List<OperationCategory> findAllOperationCategories() {
    return operationCategoryRepository.findAll();
  }

  public List<OperationCategory> findAllIncomeCategories() {
    return operationCategoryRepository.findAllByOperationTypeIs("income");
  }

  public List<OperationCategory> findAllExpenseCategories() {
    return operationCategoryRepository.findAllByOperationTypeIs("expense");
  }
}
