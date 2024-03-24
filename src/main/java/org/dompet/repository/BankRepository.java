package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Bank;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends BaseRepository<Bank>{
  Bank save(Bank bank);

  Optional<Bank> findById(String id);

  List<Bank> findAll();

  void deleteById(String id);
}
