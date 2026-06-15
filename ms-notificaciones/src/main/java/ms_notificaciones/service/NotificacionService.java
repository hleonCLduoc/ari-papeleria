package ms_notificaciones.service;

import ms_notificaciones.dto.NotificacionDTO;
import ms_notificaciones.model.Notificacion;
import ms_notificaciones.repository.NotificacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificacionService {

    // Uso de SLF4J exigido por la rúbrica para trazabilidad
    private static final Logger log = LoggerFactory.getLogger(NotificacionService.class);

    @Autowired
    private NotificacionRepository notificacionRepository;

    public Notificacion enviarNotificacion(NotificacionDTO dto){
        String mensajePersonalizado = "";
        String asunto = "Actualización de tu pedido #" + dto.getPedidoId();

        if (dto.getEstadoPedido() != null){
            switch (dto.getEstadoPedido().toUpperCase()){
                case "PRODUCCION":
                    mensajePersonalizado = "¡Tu pedido está en el área de producción!";
                    break;
                case "TERMINADO":
                    mensajePersonalizado = "¡Tu pedido ya está listo!";
                    break;
                case "ENVIADO":
                    asunto = "¡Tu pedido #" + dto.getPedidoId() + " va en camino!";
                    mensajePersonalizado = "Tu paquete ya fue entregado al repartidor. ¡Pronto estará en tus manos!";
                    break;
                default:
                    mensajePersonalizado = "El estado de tu pedido en la tienda ha cambiado a: " + dto.getEstadoPedido();
                    break;
            }
        }

        // Simulación del correo mediante Logs en consola
        log.info("=========================================================");
        log.info("📧 ENVIANDO NOTIFICACIÓN AL CLIENTE");
        log.info("Para: {}", dto.getCorreoCliente());
        log.info("Asunto: {}", asunto);
        log.info("Mensaje: {}", mensajePersonalizado);
        log.info("=========================================================");

        // Persistimos el registro para auditoría
        Notificacion registro = new Notificacion();
        registro.setCorreoDestino(dto.getCorreoCliente());
        registro.setAsunto(asunto);
        registro.setMensaje(mensajePersonalizado);
        registro.setFechaEnvio(LocalDateTime.now());

        return notificacionRepository.save(registro);
    }
}