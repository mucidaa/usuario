package com.mucida.usuario.business;

import com.mucida.usuario.business.converter.Converter;
import com.mucida.usuario.business.dto.UsuarioDTO;
import com.mucida.usuario.infrastructure.entity.Usuario;
import com.mucida.usuario.infrastructure.exceptions.ConflictException;
import com.mucida.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.mucida.usuario.infrastructure.repository.UsuarioRepository;
import com.mucida.usuario.infrastructure.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final Converter converter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioService(UsuarioRepository usuarioRepository, Converter converter, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) {
        try {
            verifyEmailConflict(usuarioDTO.getEmail());
            usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
            return converter.toUsuarioDTO(usuarioRepository.save(converter.toUsuario(usuarioDTO)));
        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage(), e.getCause());
        }
    }

    public UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado: " + email));

        usuario = converter.updateUsuario(usuarioDTO, usuario);
        usuario.setSenha(passwordEncoder.encode(usuario.getPassword()));

        return converter.toUsuarioDTO(usuario);
    }

    public UsuarioDTO findByEmail(String email) {
        return converter.toUsuarioDTO(usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado: " + email)
        ));
    }

    public void deleteByEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    private void verifyEmailConflict(String email) {
        try {
            if (verifyEmail(email)) {
                throw new ConflictException("Esse email já existe: " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage(), e.getCause());
        }
    }

    private boolean verifyEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

}
