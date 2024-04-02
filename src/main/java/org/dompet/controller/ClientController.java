package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.Account;
import org.dompet.model.Client;
import org.dompet.service.ClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
  private final ClientService clientService;

  @GetMapping
  public List<Client> findAllClients() {
    return clientService.findAllClients();
  }

  @GetMapping("/{clientId}/accounts")
  public List<Account> findAllAccounts(@PathVariable String clientId) {
    return clientService.findAllAccounts(clientId);
  }

  @GetMapping("/{id}")
  public Optional<Client> findClientById(@PathVariable String id) {
    return clientService.findClientById(id);
  }

  @PutMapping
  public Client saveClient(@RequestBody Client client) {
    return clientService.saveClient(client);
  }

  @DeleteMapping("/{id}")
  public void deleteClientById(@PathVariable String id) {
    clientService.deleteClientById(id);
  }
}
