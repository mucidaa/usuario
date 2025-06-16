package com.mucida.usuario.infrastructure.client;

import com.mucida.usuario.business.dto.InfosDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "brasil-api", url = "${brasil-api.url}")
public interface BrasilApiClient {

    @GetMapping("/cep/v1/{cep}")
    InfosDTO getInfos(@PathVariable String cep);
}
