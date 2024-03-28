package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Account;

public class AccountRepository extends CRUDOperationImpl<Account> {
    @Override
    protected Class<Account> getActualClass() {
        return Account.class;
    }
}
