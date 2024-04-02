package org.dompet.service;

import java.util.List;
import java.util.Optional;
import org.dompet.model.Account;
import org.dompet.model.Client;
import org.dompet.repository.AccountRepository;
import org.dompet.repository.ClientRepository;
import org.dompet.utils.EntityUtil;
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
    if (clientRepository.findById(client.getClientId()).isEmpty()) {
      return clientRepository.save(client);
    }
    Client existingClient =
        clientRepository
            .findById(client.getClientId())
            .orElseThrow(() -> new RuntimeException("Client not found"));
    EntityUtil.updateEntityFields(existingClient, client);
    return clientRepository.save(existingClient);
  }

  public List<Account> findAllAccounts(String ClientId) {
    return accountRepository.findByClientId(ClientId);
  }

  public Optional<Client> findClientById(String id) {
    return clientRepository.findById(id);
  }

  public List<Client> findAllClients() {
    return clientRepository.findAll();
  }

  public void deleteClientById(String id) {
    clientRepository.deleteById(id);
  }
}
