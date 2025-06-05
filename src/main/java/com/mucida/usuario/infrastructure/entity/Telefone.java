package com.mucida.usuario.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_telefone")
@Builder
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 9)
    private String numero;

    @Column(length = 2)
    private String ddd;

    @Column(name = "usuario_id")
    @JsonAlias("usuario_id")
    private Long usuarioId;
}
