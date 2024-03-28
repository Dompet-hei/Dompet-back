package org.dompet.service;

import java.util.List;

import org.dompet.model.Statement;
import org.dompet.repository.StatementRepository;
import org.springframework.stereotype.Service;

@Service
public class StatementService {
  private final StatementRepository statementRepository= new StatementRepository();

  public void createStatement(Statement statement) {
    statementRepository.insert(statement, true);
  }

  public Statement getStatementById(Integer id) {
    return statementRepository.getById(id);
  }

  public List<Statement> getAllStatements() {
    return statementRepository.getAll();
  }

  public void updateStatement(Statement statement) {
    statementRepository.save(statement);
  }

  public void deleteStatementById(Integer id) {
    statementRepository.deleteById(id);
  }
}
