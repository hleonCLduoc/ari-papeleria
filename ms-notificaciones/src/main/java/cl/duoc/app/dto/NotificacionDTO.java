package cl.duoc.app.dto;

import lombok.Data;

@Data
public class NotificacionDTO {
    private String correoCliente;
    private Long pedidoId;
    private String estadoPedido;
}
