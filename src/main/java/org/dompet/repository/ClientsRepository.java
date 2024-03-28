package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Client;
import org.dompet.utils.database.DBConnector;

public class ClientsRepository extends CRUDOperationImpl<Client> {
    public ClientsRepository(DBConnector client) {
        super(client);
    }

    @Override
    protected Class<Client> getActualClass() {
        return Client.class;
    }
}
