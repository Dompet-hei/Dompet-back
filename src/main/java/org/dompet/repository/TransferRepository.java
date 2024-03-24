package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Transfer;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends BaseRepository<Transfer>{
  Transfer save(Transfer transfer);

  Optional<Transfer> findById(String id);

  List<Transfer> findAll();

  void deleteById(String id);
}
