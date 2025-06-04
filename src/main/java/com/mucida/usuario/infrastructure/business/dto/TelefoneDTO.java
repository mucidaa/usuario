package com.mucida.usuario.infrastructure.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneDTO {

    private String numero;
    private String ddd;

}
