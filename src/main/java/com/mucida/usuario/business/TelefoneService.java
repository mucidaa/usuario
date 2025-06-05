package com.mucida.usuario.business;

import com.mucida.usuario.business.converter.Converter;
import com.mucida.usuario.business.dto.TelefoneDTO;
import com.mucida.usuario.infrastructure.entity.Telefone;
import com.mucida.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.mucida.usuario.infrastructure.repository.TelefoneRepository;
import org.springframework.stereotype.Service;

@Service
public class TelefoneService {

    private final TelefoneRepository telefoneRepository;
    private final Converter converter;

    public TelefoneService(TelefoneRepository telefoneRepository, Converter converter) {
        this.telefoneRepository = telefoneRepository;
        this.converter = converter;
    }

    public TelefoneDTO updateTelefone(Long id, TelefoneDTO telefoneDTO) {
        Telefone telefone = telefoneRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Id não encontrado: " + id));
        telefone = converter.updateTelefone(telefoneDTO, telefone);
        return converter.toTelefoneDTO(telefoneRepository.save(telefone));
    }
}
