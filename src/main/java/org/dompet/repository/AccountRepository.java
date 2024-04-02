package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CRUDOperationImpl<Account> {
  Account save(Account account);

    public AccountRepository(DBConnector account) {
        super(account);
    }

  Optional<Account> findById(String id);

  List<Account> findAll();

  void deleteById(String id);

  List<Account> findByClientId(String clientId);

    @Override
    protected Class<Account> getActualClass() {
        return Account.class;
    }
}
