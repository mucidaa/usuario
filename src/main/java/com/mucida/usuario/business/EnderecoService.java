package com.mucida.usuario.business;

import com.mucida.usuario.business.converter.Converter;
import com.mucida.usuario.business.dto.EnderecoDTO;
import com.mucida.usuario.infrastructure.entity.Endereco;
import com.mucida.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.mucida.usuario.infrastructure.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final Converter converter;

    public EnderecoService(EnderecoRepository enderecoRepository, Converter converter) {
        this.enderecoRepository = enderecoRepository;
        this.converter = converter;
    }

    public EnderecoDTO updateEndereco(Long id, EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Id não encontrado: " + id));
        endereco = converter.updateEndereco(enderecoDTO, endereco);
        return converter.toEnderecoDTO(enderecoRepository.save(endereco));
    }

}
