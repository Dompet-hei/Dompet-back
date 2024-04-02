package org.dompet.model;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@AllArgsConstructor
@Model(table = "balance")
public class Balance {
  @Id
  @Column(name = "ab_id")
  private Integer balanceId;

  @Column(name = "account_id")
  private String accountId;

  @Column(name = "balance")
  private BigDecimal balance;

  @Column(name = "last_updated")
  private Instant lastUpdated;
}
