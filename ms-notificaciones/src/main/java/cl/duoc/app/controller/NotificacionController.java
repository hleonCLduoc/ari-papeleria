package cl.duoc.app.controller;

import cl.duoc.app.dto.NotificacionDTO;
import cl.duoc.app.service.NotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping("/enviar-estado")
    public ResponseEntity<String> enviarEstadoPedido(@RequestBody NotificacionDTO dto){
        notificacionService.enviarNotificacion(dto);
        return ResponseEntity.ok("Notificación enviada exitosamente.");
    }
}
