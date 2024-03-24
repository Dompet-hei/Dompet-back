package org.dompet.repository;

import org.dompet.model.AccountView;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountViewRepository {
  AccountView findByAccountId(String accountId);
}
