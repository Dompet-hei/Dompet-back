package org.dompet.repository;

import org.dompet.model.AccountBalance;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBalanceRepository {
  AccountBalance findByAccountId(String accountId);
}
