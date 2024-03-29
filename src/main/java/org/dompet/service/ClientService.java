package org.dompet.service;

import java.util.List;

import org.dompet.model.Client;
import org.dompet.repository.ClientsRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  private final ClientsRepository clientsRepository = new ClientsRepository(null);

  public Client findById(String id) {
    return clientsRepository.getById(id);
  }

  public List<Client> findAll(){
    return clientsRepository.getAll();
  };

  public Client save(Client client) {
    clientsRepository.save(client);
    return client;
  }

  public void deleteById(String id) {
    clientsRepository.deleteById(id);
  }
}
