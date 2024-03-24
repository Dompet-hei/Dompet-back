package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account>{
  Account save(Account account);

  Optional<Account> findById(String id);

  List<Account> findAll();

  void deleteById(String id);
}
