package org.dompet.repository;

import org.dompet.jpa.CRUDOperationImpl;
import org.dompet.model.Status;

public class StatusRepository extends CRUDOperationImpl<Status> {
    @Override
    protected Class<Status> getActualClass() {
        return Status.class;
    }
}
