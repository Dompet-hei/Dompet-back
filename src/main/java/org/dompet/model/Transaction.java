package org.dompet.model;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@Model(table = "transaction")
public class Transaction {
  @Id
  @Column(name = "transaction_id")
  private String transactionId;

  @Column(name = "account_id")
  private String accountId;

  @Column(name = "category_id")
  private String categoryId;

  @Column(name = "effective_date")
  private Instant effectiveDate;

  @Column(name = "record_date")
  private Instant recordDate;

  @Column(name = "amount")
  private BigDecimal amount;

  @Column(name = "description")
  private String description;
}
