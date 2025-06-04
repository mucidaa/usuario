package com.mucida.usuario.infrastructure.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDTO {

    private String rua;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;

}
