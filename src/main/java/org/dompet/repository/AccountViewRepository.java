package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.AccountView;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class AccountViewRepository extends CRUDOperationImpl<AccountView> {
  public AccountViewRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<AccountView> getActualClass() {
    return AccountView.class;
  }

  public AccountView findByAccountId(String accountId) {
    return null;
  }
}
