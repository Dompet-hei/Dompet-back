package org.dompet.model;

import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Builder
@Data
@Model(table = "operation_category")
public class OperationCategory {
  @Id
  @Column(name = "category_id")
  private String categoryId;

  @Column(name = "name")
  private String name;

  @Column(name = "operation_type_id")
  private Integer operationType;
}
