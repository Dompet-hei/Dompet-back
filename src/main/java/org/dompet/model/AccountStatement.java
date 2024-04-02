package org.dompet.model;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

/** Mapping for DB view */
@Builder
@Data
@AllArgsConstructor
@Model(table = "vw_account_statement")
public class AccountStatement {
  @Id
  @Column(name = "account_id")
  private String accountId;

  @Column(name = "account_number")
  private Long accountNumber;

  @Column(name = "effective_date")
  private Instant effectiveDate;

  @Column(name = "category")
  private String category;

  @Column(name = "credit_amount")
  private BigDecimal creditAmount;

  @Column(name = "debit_amount")
  private BigDecimal debitAmount;

  @Column(name = "reason")
  private String reason;

  @Column(name = "operation_type")
  private String operationType;

  @Column(name = "balance")
  private BigDecimal balance;
}
