package org.dompet.model;

import java.time.LocalDate;
import lombok.*;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;

@Data
@NoArgsConstructor
@Model(table = "client")
public class Client {
  @Id
  @Column(name = "client_id")
  private String clientId;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "birth_date")
  private LocalDate birthDate;
}
