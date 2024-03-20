package org.dompet.model;

import lombok.*;
import org.jpa.utils.ID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientModel {
    @ID
    private String id;
    private String name;
    private String email;
    private String phone;
}
