package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Statement;

public class StatementRepository extends CRUDOperationImpl<Statement> {
    @Override
    protected Class<Statement> getActualClass() {
        return Statement.class;
    }
}
