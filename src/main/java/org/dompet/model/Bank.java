package org.dompet.model;

import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@AllArgsConstructor
@Model(table = "bank")
public class Bank {
  @Id
  @Column(name = "bank_id")
  private String bankId;

  @Column(name = "name")
  private String name;
}
