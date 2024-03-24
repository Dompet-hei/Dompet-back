package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Statement;
import org.dompet.repository.StatementRepository;
import org.springframework.stereotype.Service;

@Service
public class StatementService {
  public final StatementRepository statementRepository;

  public StatementService(StatementRepository statementRepository) {
    this.statementRepository = statementRepository;
  }

  public Statement save(Statement statement) {
    return statementRepository.save(statement);
  }

  public Optional<Statement> findById(String id) {
    return statementRepository.findById(id);
  }

  public List<Statement> findAll() {
    return statementRepository.findAll();
  }

  public void deleteById(String id) {
    statementRepository.deleteById(id);
  }
}
