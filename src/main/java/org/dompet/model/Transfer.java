package org.dompet.model;

import java.time.Instant;
import java.time.LocalDate;
import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Data
@NoArgsConstructor
@Model(table = "transfer")
public class Transfer {
  @Id
  @Column(name = "transfer_id")
  private String transferId;

  @Column(name = "unique_reference")
  private String uniqueReference;

  @Column(name = "scheduled_effective_date")
  private LocalDate scheduledEffectiveDate;

  @Column(name = "status_id")
  private Integer statusId;

  @Column(name = "description")
  private String description;

  @Column(name = "effective_date")
  private Instant effectiveDate;

  @Column(name = "record_date")
  private Instant recordDate;

  @Column(name = "sender_account_id")
  private String senderAccountId;

  @Column(name = "is_internal")
  private Boolean isInternal;
}
