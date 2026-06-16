package com.aripapeleria.ms_pedidos.service;

import com.aripapeleria.ms_pedidos.client.CatalogoClient;
import com.aripapeleria.ms_pedidos.client.PromocionClient;
import com.aripapeleria.ms_pedidos.client.NotificacionClient;
import com.aripapeleria.ms_pedidos.dto.NotificacionDTO;
import com.aripapeleria.ms_pedidos.dto.PedidoDTO;
import com.aripapeleria.ms_pedidos.exception.ResourceNotFoundException;
import com.aripapeleria.ms_pedidos.model.Pedido;
import com.aripapeleria.ms_pedidos.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CatalogoClient catalogoClient;

    @Autowired
    private PromocionClient promocionClient;

    @Autowired
    private NotificacionClient notificacionClient;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el pedido: " + id));
    }

    public Pedido actualizarEstado(Long pedidoId, String correo, String nuevoEstado) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el pedido: " + pedidoId));

        // Se asume que en tu modelo Pedido tienes el atributo estado.
        // Si se llama diferente, ajústalo aquí.
        pedido.setEstado(nuevoEstado);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        log.info("Pedido {} guardado con estado {}", pedidoId, nuevoEstado);

        try {
            NotificacionDTO aviso = new NotificacionDTO();
            aviso.setPedidoId(pedidoGuardado.getId());
            aviso.setCorreoCliente(correo);
            aviso.setEstadoPedido(nuevoEstado);

            notificacionClient.enviarNotificacion(aviso);
            log.info("Aviso enviado a ms-notificaciones");

        } catch (Exception e) {
            log.error("Fallo al avisar a ms-notificaciones: {}", e.getMessage());
        }

        return pedidoGuardado;
    }
}