package com.aripapeleria.ms_pedidos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "ms-productos", url = "http://localhost:8081/api/v1/productos")
public interface CatalogoClient {


    @GetMapping("/{id}")
    Object obtenerProductoPorId(@PathVariable("id") Long id);
}
