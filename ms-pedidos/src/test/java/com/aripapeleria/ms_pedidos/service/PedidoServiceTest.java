package com.aripapeleria.ms_pedidos.service;

import com.aripapeleria.ms_pedidos.client.CatalogoClient;
import com.aripapeleria.ms_pedidos.client.NotificacionClient;
import com.aripapeleria.ms_pedidos.client.PromocionClient;
import com.aripapeleria.ms_pedidos.dto.NotificacionDTO;
import com.aripapeleria.ms_pedidos.exception.ResourceNotFoundException;
import com.aripapeleria.ms_pedidos.model.Pedido;
import com.aripapeleria.ms_pedidos.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        pedido.setCliente("1");
        pedido.setProducto("1");
        pedido.setCantidad(2);
        pedido.setPrecioTotal(2400.0);
        pedido.setEstado("ENVIADO");
        pedido.setFechaCreacion(LocalDateTime.now());
    }

    @Test
    void testListarTodos() {
        // Arrange
        List<Pedido> pedidos = Arrays.asList(pedido);
        when(pedidoRepository.findAll()).thenReturn(pedidos);

        // Act
        List<Pedido> resultado = pedidoService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("ENVIADO", resultado.get(0).getEstado());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void testListarTodosVacio() {
        // Arrange
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Pedido> resultado = pedidoService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        // Arrange
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Act
        Pedido resultado = pedidoService.buscarPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("ENVIADO", resultado.getEstado());
        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorIdNoExiste() {
        // Arrange
        when(pedidoRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            pedidoService.buscarPorId(999L);
        });
        verify(pedidoRepository, times(1)).findById(999L);
    }

    @Test
    void testActualizarEstado() {
        // Arrange
        pedido.setEstado("PENDIENTE");
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
        doNothing().when(notificacionClient).enviarNotificacion(any(NotificacionDTO.class));

        // Act
        Pedido resultado = pedidoService.actualizarEstado(1L, "test@example.com", "ENVIADO");

        // Assert
        assertNotNull(resultado);
        assertEquals("ENVIADO", resultado.getEstado());
        verify(pedidoRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
        verify(notificacionClient, times(1)).enviarNotificacion(any(NotificacionDTO.class));
    }

    @Test
    void testActualizarEstadoPedidoNoExiste() {
        // Arrange
        when(pedidoRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            pedidoService.actualizarEstado(999L, "test@example.com", "ENVIADO");
        });
        verify(pedidoRepository, times(1)).findById(999L);
        verify(pedidoRepository, times(0)).save(any(Pedido.class));
    }

    @Test
    void testActualizarEstadoNotificacionFalla() {
        // Arrange
        pedido.setEstado("PENDIENTE");
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
        doThrow(new RuntimeException("Error enviando notificación"))
                .when(notificacionClient).enviarNotificacion(any(NotificacionDTO.class));

        // Act - No debe lanzar excepción aunque falle la notificación
        Pedido resultado = pedidoService.actualizarEstado(1L, "test@example.com", "ENVIADO");

        // Assert - El pedido se actualiza igualmente
        assertNotNull(resultado);
        assertEquals("ENVIADO", resultado.getEstado());
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }
}
