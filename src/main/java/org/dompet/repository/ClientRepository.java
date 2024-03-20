package org.dompet.repository;

import org.dompet.model.ClientModel;
import org.jpa.CRUDOperationImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository extends CRUDOperationImpl<ClientModel> {
    @Override
    protected Class<ClientModel> getActualClass() {
        return ClientModel.class;
    }
}
