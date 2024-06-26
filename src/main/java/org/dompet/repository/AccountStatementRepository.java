package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Account;
import org.dompet.model.AccountStatement;
import org.dompet.utils.annotations.IdAnnotation;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class AccountStatementRepository extends CRUDOperationImpl<AccountStatement> {
  public AccountStatementRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<AccountStatement> getActualClass() {
    return AccountStatement.class;
  }

  public List<AccountStatement> findAllByAccountId(String accountId) {
    List<AccountStatement> accountStatements =
        getAllWithCondition(
            IdAnnotation.getIdColumnName(Account.class) + " = ?", null, null, accountId);
    return accountStatements.isEmpty() ? null : accountStatements;
  }
}
