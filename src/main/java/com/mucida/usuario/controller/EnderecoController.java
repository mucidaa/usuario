package com.mucida.usuario.controller;

import com.mucida.usuario.business.ViaCepService;
import com.mucida.usuario.business.EnderecoService;
import com.mucida.usuario.business.dto.EnderecoDTO;
import com.mucida.usuario.business.dto.InfosDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final ViaCepService viaCepService;

    public EnderecoController(EnderecoService enderecoService, ViaCepService viaCepService) {
        this.enderecoService = enderecoService;
        this.viaCepService = viaCepService;
    }

    @PutMapping
    public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody EnderecoDTO enderecoDTO, @RequestParam Long id) {
        return ResponseEntity.ok(enderecoService.updateEndereco(id, enderecoDTO));
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> saveEndereco(@RequestBody EnderecoDTO enderecoDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(enderecoService.saveEndereco(enderecoDTO, token));
    }

    @GetMapping("/{cep}")
    public ResponseEntity<InfosDTO> getInfos(@PathVariable String cep) {
        return ResponseEntity.ok(viaCepService.getInfos(cep));
    }

}
