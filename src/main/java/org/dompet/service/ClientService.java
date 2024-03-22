package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Client;
import org.dompet.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  public final ClientRepository clientRepository;

  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Client save(Client client) {
    return clientRepository.save(client);
  }

  public Optional<Client> findById(String id) {
    return clientRepository.findById(id);
  }

  public List<Client> findAll() {
    return clientRepository.findAll();
  }

  public void deleteById(String id) {
    clientRepository.deleteById(id);
  }
}
