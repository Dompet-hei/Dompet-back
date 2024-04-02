package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Balance;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository {
  Balance save(Balance balance);

  Optional<Balance> findById(Integer id);

  List<Balance> findAll();

  void deleteById(String id);

  List<Balance> findAllByAccountId(String accountId);

  Balance findFirstByAccountIdOrderByLastUpdatedDesc(String accountId);
}
