package com.mucida.usuario.business;

import com.mucida.usuario.business.converter.Converter;
import com.mucida.usuario.business.dto.UsuarioDTO;
import com.mucida.usuario.infrastructure.entity.Usuario;
import com.mucida.usuario.infrastructure.exceptions.ConflictException;
import com.mucida.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.mucida.usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final Converter converter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, Converter converter, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
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

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado: " + email)
        );
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
