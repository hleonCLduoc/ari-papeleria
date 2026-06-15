package ms_notificaciones.controller;

import ms_notificaciones.dto.NotificacionDTO;
import ms_notificaciones.model.Notificacion;
import ms_notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    // Endpoint seguro con @Valid para disparar Bean Validation
    @PostMapping
    public ResponseEntity<Notificacion> recibirNotificacion(@Valid @RequestBody NotificacionDTO dto) {
        Notificacion registro = notificacionService.enviarNotificacion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro); // Devuelve 201 Created
    }
}