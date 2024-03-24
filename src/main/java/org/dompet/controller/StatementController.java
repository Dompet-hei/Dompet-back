package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.Statement;
import org.dompet.service.StatementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/statement")
public class StatementController {
  private final StatementService statementService;

  @GetMapping
  public List<Statement> findAllStatements() {
    return statementService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Statement> findStatementById(@PathVariable String id) {
    return statementService.findById(id);
  }

  @PutMapping
  public Statement saveStatement(@RequestBody Statement statement) {
    return statementService.save(statement);
  }

  @DeleteMapping("/{id}")
  public void deleteStatementById(@PathVariable String id) {
    statementService.deleteById(id);
  }
}
