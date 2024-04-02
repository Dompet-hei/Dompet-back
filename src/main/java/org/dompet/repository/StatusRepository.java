package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Status;
import org.dompet.utils.database.DBConnector;

public class StatusRepository extends CRUDOperationImpl<Status> {
    public StatusRepository(DBConnector status) {
        super(status);
    }
    @Override
    protected Class<Status> getActualClass() {
        return Status.class;
    }
}
