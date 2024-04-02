package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.AccountStatement;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatementRepository {
  AccountStatement save(AccountStatement accountStatement);

  Optional<AccountStatement> findById(String id);

  List<AccountStatement> findAll();

  void deleteById(String id);

  List<AccountStatement> findAllByAccountId(String accountId);
}
