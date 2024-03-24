package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.OperationType;
import org.dompet.repository.OperationTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationTypeService {
  public final OperationTypeRepository operationTypeRepository;

  public OperationTypeService(OperationTypeRepository operationTypeRepository) {
    this.operationTypeRepository = operationTypeRepository;
  }

  public OperationType save(OperationType operationType) {
    return operationTypeRepository.save(operationType);
  }

  public Optional<OperationType> findById(String id) {
    return operationTypeRepository.findById(id);
  }

  public List<OperationType> findAll() {
    return operationTypeRepository.findAll();
  }

  public void deleteById(String id) {
    operationTypeRepository.deleteById(id);
  }
}
