package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Overdraft;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class OverdraftRepository extends CRUDOperationImpl<Overdraft> {
  public OverdraftRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<Overdraft> getActualClass() {
    return Overdraft.class;
  }

  public List<Overdraft> findByAccountId(String accountId) {
    return null;
  }
  ;

  public Overdraft findByAccountIdAndOverdraftId(String accountId, String overdraftId) {
    return null;
  }
  ;
}
