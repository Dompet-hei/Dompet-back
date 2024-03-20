package org.dompet.services;

import lombok.AllArgsConstructor;
import org.dompet.model.ClientModel;
import org.dompet.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;

    public List<ClientModel> getAll(){
        return clientRepository.getAll();
    };
}
