package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Client;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository extends CRUDOperationImpl<Client> {
  public ClientRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<Client> getActualClass() {
    return Client.class;
  }
}
