package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Client;

public class ClientsRepository extends CRUDOperationImpl<Client> {
    @Override
    protected Class<Client> getActualClass() {
        return Client.class;
    }
}
