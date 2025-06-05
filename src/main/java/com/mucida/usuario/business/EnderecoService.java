package com.mucida.usuario.business;

import com.mucida.usuario.business.converter.Converter;
import com.mucida.usuario.business.dto.EnderecoDTO;
import com.mucida.usuario.infrastructure.entity.Endereco;
import com.mucida.usuario.infrastructure.entity.Usuario;
import com.mucida.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.mucida.usuario.infrastructure.repository.EnderecoRepository;
import com.mucida.usuario.infrastructure.repository.UsuarioRepository;
import com.mucida.usuario.infrastructure.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final Converter converter;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, Converter converter, JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
        this.enderecoRepository = enderecoRepository;
        this.converter = converter;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
    }

    public EnderecoDTO updateEndereco(Long id, EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Id não encontrado: " + id));
        endereco = converter.updateEndereco(enderecoDTO, endereco);
        return converter.toEnderecoDTO(enderecoRepository.save(endereco));
    }

    public EnderecoDTO saveEndereco(EnderecoDTO enderecoDTO, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado: " + email)
        );
        Endereco endereco = enderecoRepository.save(converter.toEndereco(enderecoDTO, usuario.getId()));
        return converter.toEnderecoDTO(endereco);
    }

}
