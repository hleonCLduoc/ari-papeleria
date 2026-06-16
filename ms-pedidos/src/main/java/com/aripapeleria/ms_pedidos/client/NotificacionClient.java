package com.aripapeleria.ms_pedidos.client;

import com.aripapeleria.ms_pedidos.dto.NotificacionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//  nombre del servicio y la URL exacta de ms notificaciones
@FeignClient(name = "ms-notificaciones", url = "http://localhost:8086/api/v1/notificaciones")
public interface NotificacionClient {

    // Dispara el JSON mediante un método POST
    @PostMapping
    Object enviarNotificacion(@RequestBody NotificacionDTO dto);
}