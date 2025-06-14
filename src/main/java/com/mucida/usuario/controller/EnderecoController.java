package com.mucida.usuario.controller;

import com.mucida.usuario.business.EnderecoService;
import com.mucida.usuario.business.dto.EnderecoDTO;
import com.mucida.usuario.business.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PutMapping
    public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody EnderecoDTO enderecoDTO, @RequestParam Long id) {
        return ResponseEntity.ok(enderecoService.updateEndereco(id, enderecoDTO));
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> saveEndereco(@RequestBody EnderecoDTO enderecoDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(enderecoService.saveEndereco(enderecoDTO, token));
    }

}
