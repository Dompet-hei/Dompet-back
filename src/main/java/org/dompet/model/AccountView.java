package org.dompet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@Model(table = "account_view")
public class AccountView {
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

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "bank_id")
  private String bankId;

  @Column(name = "bank_name")
  private String bankName;
}
