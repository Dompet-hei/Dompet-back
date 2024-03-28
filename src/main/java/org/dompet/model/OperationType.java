package org.dompet.model;

import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@AllArgsConstructor
@Model(table = "operation_type")
public class OperationType {
  @Id
  @Column(name = "operation_type_id")
  private Integer id;

  @Column(name = "name")
  private String name;
}
