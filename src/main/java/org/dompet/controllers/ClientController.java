package org.dompet.controllers;

import lombok.AllArgsConstructor;
import org.dompet.model.ClientModel;
import org.dompet.services.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    @GetMapping
    public List<ClientModel> getAll(){
        return clientService.getAll();
    };
}
