package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Overdraft;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public interface OverdraftRepository  extends CRUDOperationImpl<Overdraft>{
  Overdraft save(Overdraft overdraft);

    public OverdraftRepository(DBConnector bank) {
        super(bank);
    }
    @Override
    protected Class<Overdraft> getActualClass() {
        return Overdraft.class;
    }

  Optional<Overdraft> findById(String id);

  List<Overdraft> findAll();

  void deleteById(String id);

  List<Overdraft> findByAccountId(String accountId);

  Overdraft findByAccountIdAndOverdraftId(String accountId, String overdraftId);

}
