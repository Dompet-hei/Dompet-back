package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Statement;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository {
  Statement save(Statement statement);

  Optional<Statement> findById(String id);

  List<Statement> findAll();

  void deleteById(String id);
}
