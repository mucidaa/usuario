package com.mucida.usuario.business;

import com.mucida.usuario.business.dto.InfosDTO;
import com.mucida.usuario.infrastructure.client.ViaCepClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient client;

    public InfosDTO getInfos(String cep) {
        return client.getInfos(trimCep(cep));
    }

    private String trimCep(String cep) {

        cep = cep.replaceAll("[ -]", "");

        if (!cep.matches("[0-9]+") || cep.length() != 8) {
            throw new IllegalArgumentException("Formatação errada do CEP " + cep);
        }

        return cep;
    }
}
