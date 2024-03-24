package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Overdraft;
import org.springframework.stereotype.Repository;

@Repository
public interface OverdraftRepository extends BaseRepository<Overdraft>{
  Overdraft save(Overdraft overdraft);

  Optional<Overdraft> findById(String id);

  List<Overdraft> findAll();

  void deleteById(String id);
}
