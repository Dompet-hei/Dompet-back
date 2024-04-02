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

  public List<Overdraft> findAllByAccountId(String accountId) {
    List<Overdraft> overdrafts = getAllWithCondition("account_id = ?", null, null, accountId);
    return overdrafts.isEmpty() ? null : overdrafts;
  }

  public Overdraft findByAccountIdAndOverdraftId(String accountId, String overdraftId) {
    List<Overdraft> overdrafts =
        getAllWithCondition(
            "account_id = ? AND overdraft_id = ?", accountId, null, null, overdraftId);
    return overdrafts.isEmpty() ? null : overdrafts.get(0);
  }
}
