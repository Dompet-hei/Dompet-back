package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.OperationCategory;
import org.dompet.service.OperationCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class OperationCategoryController {
  private final OperationCategoryService operationCategoryService;

  @GetMapping
  public List<OperationCategory> findAllOperationCategory() {
    return operationCategoryService.findAllOperationCategories();
  }

  @GetMapping("/{id}")
  public Optional<OperationCategory> findOperationCategoryById(@PathVariable String id) {
    return operationCategoryService.findOperationCategoryById(id);
  }

  @GetMapping("/income")
  public List<OperationCategory> findAllIncomeCategories() {
    return operationCategoryService.findAllIncomeCategories();
  }

  @GetMapping("/expense")
  public List<OperationCategory> findAllExpenseCategories() {
    return operationCategoryService.findAllExpenseCategories();
  }
}
