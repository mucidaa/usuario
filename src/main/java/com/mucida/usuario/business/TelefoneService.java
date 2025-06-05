package com.mucida.usuario.business;

import com.mucida.usuario.business.converter.Converter;
import com.mucida.usuario.business.dto.EnderecoDTO;
import com.mucida.usuario.business.dto.TelefoneDTO;
import com.mucida.usuario.infrastructure.entity.Endereco;
import com.mucida.usuario.infrastructure.entity.Telefone;
import com.mucida.usuario.infrastructure.entity.Usuario;
import com.mucida.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.mucida.usuario.infrastructure.repository.TelefoneRepository;
import com.mucida.usuario.infrastructure.repository.UsuarioRepository;
import com.mucida.usuario.infrastructure.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class TelefoneService {

    private final TelefoneRepository telefoneRepository;
    private final Converter converter;
    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public TelefoneService(TelefoneRepository telefoneRepository, Converter converter, UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.telefoneRepository = telefoneRepository;
        this.converter = converter;
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    public TelefoneDTO updateTelefone(Long id, TelefoneDTO telefoneDTO) {
        Telefone telefone = telefoneRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Id não encontrado: " + id));
        telefone = converter.updateTelefone(telefoneDTO, telefone);
        return converter.toTelefoneDTO(telefoneRepository.save(telefone));
    }

    public TelefoneDTO saveTelefone(TelefoneDTO telefoneDTO, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado: " + email)
        );
        Telefone telefone = converter.toTelefone(telefoneDTO, usuario.getId());
        return converter.toTelefoneDTO(telefoneRepository.save(telefone));
    }
}
