package com.aripapeleria.ms_pedidos.dto;

import lombok.Data;

@Data
public class NotificacionDTO {
    private Long pedidoId;
    private String correoCliente;
    private String estadoPedido;
}