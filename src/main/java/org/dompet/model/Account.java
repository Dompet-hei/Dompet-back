package org.dompet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@Model(table = "account")
public class Account {
  @Id
  @Column(name = "account_id")
  private String accountId;

  @Column(name = "account_number")
  private Long accountNumber;

  @Column(name = "creation_date")
  private LocalDate creationDate;

  @Column(name = "monthly_net_salary")
  private BigDecimal monthlyNetSalary;

  @Column(name = "default_currency_code")
  private String defaultCurrencyCode;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "client_id")
  private String clientId;

  @Column(name = "bank_id")
  private String bankId;
}
