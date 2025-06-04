package com.mucida.usuario.infrastructure.business;

import com.mucida.usuario.infrastructure.business.converter.Converter;
import com.mucida.usuario.infrastructure.business.dto.UsuarioDTO;
import com.mucida.usuario.infrastructure.entity.Usuario;
import com.mucida.usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final Converter converter;

    public UsuarioService(UsuarioRepository usuarioRepository, Converter converter) {
        this.usuarioRepository = usuarioRepository;
        this.converter = converter;
    }

    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) {
        return converter.toUsuarioDTO(usuarioRepository.save(converter.toUsuario(usuarioDTO)));
    }

}
