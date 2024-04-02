package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Account;
import org.dompet.model.Client;
import org.dompet.repository.AccountRepository;
import org.dompet.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  public final ClientRepository clientRepository;
  public final AccountRepository accountRepository;

  public ClientService(ClientRepository clientRepository, AccountRepository accountRepository) {
    this.clientRepository = clientRepository;
    this.accountRepository = accountRepository;
  }

  public Client saveClient(Client client) {
    //    if (clientRepository.getById(client.getClientId()).isEmpty()) {
    return clientRepository.insert(client, true);
    //    }
    //    Client existingClient =
    //        clientRepository
    //            .getById(client.getClientId())
    //            .orElseThrow(() -> new RuntimeException("Client not found"));
    //    EntityUtil.updateEntityFields(existingClient, client);
    //    return clientRepository.insert(existingClient, true);
  }

  public List<Account> findAllAccounts(String ClientId) {
    return accountRepository.findByClientId(ClientId);
  }

  public Optional<Client> findClientById(String id) {
    return clientRepository.getById(id);
  }

  public List<Client> findAllClients() {
    return clientRepository.getAll();
  }

  public void deleteClientById(String id) {
    clientRepository.deleteById(id);
  }
}
