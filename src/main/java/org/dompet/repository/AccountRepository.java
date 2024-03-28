package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Account;
import org.dompet.utils.database.DBConnector;

public class AccountRepository extends CRUDOperationImpl<Account> {

    public AccountRepository(DBConnector account) {
        super(account);
    }

    @Override
    protected Class<Account> getActualClass() {
        return Account.class;
    }
}
