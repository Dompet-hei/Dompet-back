package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Bank;

public class BankRepository extends CRUDOperationImpl<Bank> {
    @Override
    protected Class<Bank> getActualClass() {
        return Bank.class;
    }
}
