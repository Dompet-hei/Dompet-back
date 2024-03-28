package org.dompet.service;

import java.util.List;

import org.dompet.model.OperationType;
import org.dompet.repository.OperationTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationTypeService {
  private final OperationTypeRepository operationTypeRepository= new OperationTypeRepository();

  public void createOperationType(OperationType operationType) {
    operationTypeRepository.insert(operationType, true);
  }

  public OperationType getBankById(Integer id) {
    return operationTypeRepository.getById(id);
  }

  public List<OperationType> getAllOperationTypes() {
    return operationTypeRepository.getAll();
  }

  public void updateBank(OperationType operationType) {
    operationTypeRepository.save(operationType);
  }

  public void deleteBankById(Integer id) {
    operationTypeRepository.deleteById(id);
  }
}
