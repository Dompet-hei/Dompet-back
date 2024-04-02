package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Account;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository extends CRUDOperationImpl<Account> {
  public AccountRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<Account> getActualClass() {
    return Account.class;
  }

  public List<Account> findByClientId(String clientId) {
    return null;
  }
}
