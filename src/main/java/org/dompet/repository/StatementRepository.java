package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Statement;
import org.dompet.utils.database.DBConnector;

public class StatementRepository extends CRUDOperationImpl<Statement> {
    public StatementRepository(DBConnector statement) {
        super(statement);
    }
    @Override
    protected Class<Statement> getActualClass() {
        return Statement.class;
    }
}
