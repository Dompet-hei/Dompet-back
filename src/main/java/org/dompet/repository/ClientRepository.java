package org.dompet.repository;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends BaseRepository<Client>{
  Client save(Client client);

  Optional<Client> findById(String id);

  List<Client> findAll();

  void deleteById(String id);
}
