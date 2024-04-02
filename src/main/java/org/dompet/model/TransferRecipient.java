package org.dompet.model;

import java.math.BigDecimal;
import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Data
@NoArgsConstructor
@Model(table = "transfer_recipient")
public class TransferRecipient {
  @Id
  @Column(name = "transfer_recipient_id")
  private Long transferRecipientId;

  @Column(name = "transfer_id")
  private String transferId;

  @Column(name = "recipient_account_id")
  private String recipientAccountId;

  @Column(name = "amount")
  private BigDecimal amount;
}
