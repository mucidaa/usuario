package com.mucida.usuario.business;

import com.mucida.usuario.business.dto.InfosDTO;
import com.mucida.usuario.infrastructure.client.BrasilApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrasilApiService {

    private final BrasilApiClient client;

    public InfosDTO getInfos(String cep) {
        return client.getInfos(trimCep(cep));
    }

    private String trimCep(String cep) {
        cep = cep.replaceAll("- ", "");

        if (cep.matches("[0-9]")) {
            throw new IllegalArgumentException("Formatação errada do CEP" + cep);
        }

        return cep;
    }
}
