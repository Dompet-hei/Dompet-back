package org.dompet.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

/** Mapping for DB view */
@Builder
@Data
@AllArgsConstructor
@Model(table = "transfer_details")
public class TransferDetail {
  @Id
  @Column(name = "transfer_id")
  private String transferId;

  @Column(name = "unique_reference")
  private String uniqueReference;

  @Column(name = "scheduled_effective_date")
  private LocalDate scheduledEffectiveDate;

  @Column(name = "transfer_status")
  private String transferStatus;

  @Column(name = "description")
  private String description;

  @Column(name = "effective_date")
  private Instant effectiveDate;

  @Column(name = "amount")
  private BigDecimal amount;

  @Column(name = "sender_account_id")
  private String senderAccountId;

  @Column(name = "recipient_account_id")
  private String recipientAccountId;

  @Column(name = "sender_account")
  private Long senderAccount;

  @Column(name = "recipient_account")
  private Long recipientAccount;
}
