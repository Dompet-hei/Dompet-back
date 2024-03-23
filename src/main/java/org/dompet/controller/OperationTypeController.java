package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.OperationType;
import org.dompet.service.OperationTypeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/operationType")
public class OperationTypeController {
  private final OperationTypeService operationTypeService;

  @GetMapping
  public List<OperationType> findAllOperationType() {
    return operationTypeService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<OperationType> findOperationTypeById(@PathVariable String id) {
    return operationTypeService.findById(id);
  }

  @PutMapping
  public OperationType saveOperationType(@RequestBody OperationType operationType) {
    return operationTypeService.save(operationType);
  }

  @DeleteMapping("/{id}")
  public void deleteOperationTypeById(@PathVariable String id) {
    operationTypeService.deleteById(id);
  }
}
