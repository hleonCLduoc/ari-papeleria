package com.aripapeleria.ms_pedidos.service;

import com.aripapeleria.ms_pedidos.client.CatalogoClient;
import com.aripapeleria.ms_pedidos.client.NotificacionClient;
import com.aripapeleria.ms_pedidos.client.PromocionClient;
import com.aripapeleria.ms_pedidos.dto.NotificacionDTO;
import com.aripapeleria.ms_pedidos.exception.ResourceNotFoundException;
import com.aripapeleria.ms_pedidos.model.Pedido;
import com.aripapeleria.ms_pedidos.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias de PedidoService.
 * Se usan mocks (Mockito) para el repositorio y los clientes Feign,
 * de modo que se prueba solo la logica de negocio sin base de datos ni red.
 */
@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private CatalogoClient catalogoClient;

    @Mock
    private PromocionClient promocionClient;

    @Mock
    private NotificacionClient notificacionClient;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente("Ana");
        pedido.setProducto("Cuaderno");
        pedido.setCantidad(2);
        pedido.setPrecioTotal(3990.0);
        pedido.setEstado("Pendiente de Pago");
    }

    @Test
    @DisplayName("listarTodos devuelve la lista del repositorio")
    void listarTodos_devuelveLista() {
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> resultado = pedidoService.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Ana", resultado.get(0).getCliente());
        verify(pedidoRepository).findAll();
    }

    @Test
    @DisplayName("buscarPorId devuelve el pedido cuando existe")
    void buscarPorId_existente() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido resultado = pedidoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(pedidoRepository).findById(1L);
    }

    @Test
    @DisplayName("buscarPorId lanza ResourceNotFoundException cuando no existe")
    void buscarPorId_inexistente() {
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> pedidoService.buscarPorId(99L));
        verify(pedidoRepository).findById(99L);
    }

    @Test
    @DisplayName("actualizarEstado guarda el nuevo estado y envia la notificacion")
    void actualizarEstado_ok() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(inv -> inv.getArgument(0));

        Pedido resultado = pedidoService.actualizarEstado(1L, "ana@mail.com", "Enviado");

        assertEquals("Enviado", resultado.getEstado());
        verify(pedidoRepository).save(pedido);
        verify(notificacionClient).enviarNotificacion(any(NotificacionDTO.class));
    }

    @Test
    @DisplayName("actualizarEstado lanza excepcion si el pedido no existe")
    void actualizarEstado_inexistente() {
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> pedidoService.actualizarEstado(99L, "x@mail.com", "Enviado"));
        verify(pedidoRepository, never()).save(any());
        verify(notificacionClient, never()).enviarNotificacion(any());
    }

    @Test
    @DisplayName("actualizarEstado no falla aunque la notificacion arroje error")
    void actualizarEstado_notificacionFalla_noRompe() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(inv -> inv.getArgument(0));
        doThrow(new RuntimeException("ms-notificaciones caido"))
                .when(notificacionClient).enviarNotificacion(any(NotificacionDTO.class));

        Pedido resultado = pedidoService.actualizarEstado(1L, "ana@mail.com", "Entregado");

        // El estado se guarda igual; el fallo de notificacion se captura y no propaga
        assertEquals("Entregado", resultado.getEstado());
        verify(pedidoRepository).save(pedido);
    }
}
