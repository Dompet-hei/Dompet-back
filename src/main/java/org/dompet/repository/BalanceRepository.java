package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Balance;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceRepository extends CRUDOperationImpl<Balance> {
  public BalanceRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<Balance> getActualClass() {
    return Balance.class;
  }

  public List<Balance> findAllByAccountId(String accountId) {
    List<Balance> balances = getAllWithCondition("account_id = ?", null, null, accountId);
    return balances.isEmpty() ? null : balances;
  }

  public Balance findFirstByAccountIdOrderByLastUpdatedDesc(String accountId) {
    List<Balance> balances =
        getAllWithCondition("account_id = ?", "last_updated DESC", 1, accountId);
    return balances.isEmpty() ? null : balances.get(0);
  }
}
