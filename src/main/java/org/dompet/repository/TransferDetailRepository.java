package org.dompet.repository;

import org.dompet.model.TransferDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferDetailRepository {
    List<TransferDetail> findAll();
    List<TransferDetail> findAllByAccountId(String accountId);
}
