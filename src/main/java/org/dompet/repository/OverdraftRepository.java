package org.dompet.repository;

import java.util.List;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Account;
import org.dompet.model.Overdraft;
import org.dompet.utils.annotations.IdAnnotation;
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
    List<Overdraft> overdrafts =
        getAllWithCondition(
            IdAnnotation.getIdColumnName(Account.class) + " = ?", null, null, accountId);
    return overdrafts.isEmpty() ? null : overdrafts;
  }

  public Overdraft findByAccountIdAndOverdraftId(String accountId, String overdraftId) {
    List<Overdraft> overdrafts =
        getAllWithCondition(
            IdAnnotation.getIdColumnName(Account.class)
                + " = ? AND "
                + IdAnnotation.getIdColumnName(Overdraft.class)
                + " = ?",
            null,
            null,
            accountId,
            overdraftId);
    return overdrafts.isEmpty() ? null : overdrafts.get(0);
  }
}
