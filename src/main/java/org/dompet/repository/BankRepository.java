package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Bank;
import org.dompet.utils.database.DBConnector;
import org.springframework.stereotype.Repository;

@Repository
public class BankRepository extends CRUDOperationImpl<Bank> {

  public BankRepository(DBConnector dbConnector) {
    super(dbConnector);
  }

  @Override
  protected Class<Bank> getActualClass() {
    return Bank.class;
  }
}
