package org.dompet.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@Model(table = "account_balances")
public class AccountBalance {
  @Id
  @Column(name = "account_id")
  private String accountId;

  @Column(name = "client_id")
  private String clientId;

  @Column(name = "account_number")
  private Long accountNumber;

  @Column(name = "balance")
  private BigDecimal balance;
}
