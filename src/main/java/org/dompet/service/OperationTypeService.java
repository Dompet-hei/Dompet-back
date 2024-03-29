package org.dompet.service;

import java.util.List;

import org.dompet.model.OperationType;
import org.dompet.repository.OperationTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationTypeService {
  private final OperationTypeRepository operationTypeRepository= new OperationTypeRepository(null);

  public OperationType findById(String id) {
    return operationTypeRepository.getById(id);
  }

  public List<OperationType> findAll() {
    return operationTypeRepository.getAll();
  }

  public OperationType save(OperationType operationType) {
    operationTypeRepository.save(operationType);
    return operationType;
  }

  public void deleteById(String id) {
    operationTypeRepository.deleteById(id);
  }
}
