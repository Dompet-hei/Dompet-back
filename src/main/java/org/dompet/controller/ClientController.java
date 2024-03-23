package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.Client;
import org.dompet.service.ClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/client")
public class ClientController {
  private final ClientService clientService;

  @GetMapping
  public List<Client> findAllClients() {
    return clientService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Client> findClientById(@PathVariable String id) {
    return clientService.findById(id);
  }

  @PutMapping
  public Client saveClient(@RequestBody Client client) {
    return clientService.save(client);
  }

  @DeleteMapping("/{id}")
  public void deleteClientById(@PathVariable String id) {
    clientService.deleteById(id);
  }
}
