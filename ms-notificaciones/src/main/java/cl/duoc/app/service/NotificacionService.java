package cl.duoc.app.service;

import cl.duoc.app.dto.NotificacionDTO;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    public void enviarNotificacion(NotificacionDTO dto){
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
        System.out.println("=========================================================");
        System.out.println("ENVIANDO NOTIFICACIÓN AL CLIENTE");
        System.out.println("Para: " + dto.getCorreoCliente());
        System.out.println("Asunto: " + asunto);
        System.out.println("Mensaje: " + mensajePersonalizado);
        System.out.println("=========================================================");
    }
}
