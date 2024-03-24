package org.dompet.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransferRecipientId implements Serializable {
  private String transferId;
  private String recipientAccountId;
}
