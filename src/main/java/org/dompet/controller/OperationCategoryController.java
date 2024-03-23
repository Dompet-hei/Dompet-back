package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.OperationCategory;
import org.dompet.service.OperationCategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/categories")
public class OperationCategoryController {
  private final OperationCategoryService operationCategoryService;

  @GetMapping
  public List<OperationCategory> findAllOperationCategory() {
    return operationCategoryService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<OperationCategory> findOperationCategoryById(@PathVariable String id) {
    return operationCategoryService.findById(id);
  }

  @PutMapping
  public OperationCategory saveOperationCategory(@RequestBody OperationCategory operationCategory) {
    return operationCategoryService.save(operationCategory);
  }

  @DeleteMapping("/{id}")
  public void deleteOperationCategoryById(@PathVariable String id) {
    operationCategoryService.deleteById(id);
  }
}
