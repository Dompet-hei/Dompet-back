package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Overdraft;

public class OverdraftRepository extends CRUDOperationImpl<Overdraft> {
    @Override
    protected Class<Overdraft> getActualClass() {
        return Overdraft.class;
    }
}
