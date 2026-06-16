package com.aripapeleria.ms_pedidos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-promociones", url = "http://localhost:8084/api/v1/promociones")
public interface PromocionClient {

    // AHORA BUSCAMOS POR CÓDIGO (String)
    @GetMapping("/codigo/{codigo}")
    Object obtenerPromocionPorCodigo(@PathVariable("codigo") String codigo);
}