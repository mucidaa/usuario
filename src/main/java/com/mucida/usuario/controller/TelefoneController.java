package com.mucida.usuario.controller;

import com.mucida.usuario.business.TelefoneService;
import com.mucida.usuario.business.dto.TelefoneDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {

    private final TelefoneService telefoneService;

    public TelefoneController(TelefoneService telefoneService) {
        this.telefoneService = telefoneService;
    }

    @PutMapping
    public ResponseEntity<TelefoneDTO> updateTelefone(@RequestBody TelefoneDTO telefoneDTO, @RequestParam Long id) {
        return ResponseEntity.ok(telefoneService.updateTelefone(id, telefoneDTO));
    }
}
