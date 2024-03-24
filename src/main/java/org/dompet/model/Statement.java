package org.dompet.model;

import java.time.LocalDate;
import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@Model(table = "statement")
public class Statement {
  @Id
  @Column(name = "statement_id")
  private String statementId;

  @Column(name = "account_id")
  private String accountId;

  @Column(name = "start_period")
  private LocalDate startPeriod;

  @Column(name = "end_period")
  private LocalDate endPeriod;
}
