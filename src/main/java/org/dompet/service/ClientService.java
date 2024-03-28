package org.dompet.service;

import java.util.List;

import org.dompet.model.Client;
import org.dompet.repository.ClientsRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  private final ClientsRepository clientsRepository = new ClientsRepository();

  public void createClient(Client client) {
    clientsRepository.insert(client, true);
  }

  public Client getClientById(Integer id) {
    return clientsRepository.getById(id);
  }

  public List<Client> getAllClients(){
    return clientsRepository.getAll();
  };

  public void updateClient(Client client) {
    clientsRepository.save(client);
  }

  public void deleteClientById(Integer id) {
    clientsRepository.deleteById(id);
  }
}
