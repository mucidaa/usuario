package com.mucida.usuario.infrastructure.client;

import com.mucida.usuario.business.dto.InfosDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep", url = "${via-cep.url}")
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json")
    InfosDTO getInfos(@PathVariable String cep);
}
