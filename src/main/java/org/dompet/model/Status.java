package org.dompet.model;

import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@AllArgsConstructor
@Model(table = "status")
public class Status {
  @Id
  @Column(name = "status_id")
  private Integer statusId;

  @Column(name = "name")
  private String name;
}
