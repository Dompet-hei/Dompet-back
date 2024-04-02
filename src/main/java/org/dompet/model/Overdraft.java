package org.dompet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@AllArgsConstructor
@Model(table = "overdraft")
public class Overdraft {
  @Id
  @Column(name = "overdraft_id")
  private String overdraftId;

  @Column(name = "account_id")
  private String accountId;

  @Column(name = "overdraft_allowed")
  private Boolean overdraftAllowed;

  @Column(name = "overdraft_balance")
  private BigDecimal overdraftBalance;

  @Column(name = "overdraft_start_date")
  private LocalDate overdraftStartDate;

  @Column(name = "overdraft_reimbursement_date")
  private LocalDate overdraftReimbursementDate;
}
