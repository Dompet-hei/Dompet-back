package org.dompet.service;

import java.util.List;

import org.dompet.model.Statement;
import org.dompet.repository.StatementRepository;
import org.springframework.stereotype.Service;

@Service
public class StatementService {
  private final StatementRepository statementRepository= new StatementRepository(null);

  public Statement findById(String id) {
    return statementRepository.getById(id);
  }

  public List<Statement> findAll() {
    return statementRepository.getAll();
  }

  public Statement save(Statement statement) {
    statementRepository.save(statement);
    return statement;
  }

  public void deleteById(String id) {
    statementRepository.deleteById(id);
  }
}
