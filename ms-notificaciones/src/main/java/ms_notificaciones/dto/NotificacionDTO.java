package ms_notificaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificacionDTO {

    @NotNull(message = "El ID del pedido es obligatorio")
    private Long pedidoId;

    @NotBlank(message = "El correo del cliente es obligatorio")
    private String correoCliente;

    @NotBlank(message = "El estado del pedido es obligatorio")
    private String estadoPedido;
}