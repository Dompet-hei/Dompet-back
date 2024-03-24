package org.dompet.model;

import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@Model(table = "transfer_recipient")
public class TransferRecipient {
  @Id private TransferRecipientId id;

  @Column(name = "transfer_id")
  private Transfer transferId;

  @Column(name = "recipient_account_id")
  private Account recipientAccountId;
}
