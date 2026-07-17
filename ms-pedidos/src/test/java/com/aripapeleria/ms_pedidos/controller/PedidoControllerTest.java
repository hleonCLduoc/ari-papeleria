package com.aripapeleria.ms_pedidos.controller;

import com.aripapeleria.ms_pedidos.model.Pedido;
import com.aripapeleria.ms_pedidos.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.aripapeleria.ms_pedidos.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

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
    void testListarPedidos() throws Exception {
        // Arrange
        when(pedidoService.listarTodos()).thenReturn(Arrays.asList(pedido));

        // Act & Assert
        mockMvc.perform(get("/api/v1/pedidos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].estado").value("ENVIADO"));

        verify(pedidoService, times(1)).listarTodos();
    }

    @Test
    void testListarPedidosVacio() throws Exception {
        // Arrange
        when(pedidoService.listarTodos()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/v1/pedidos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(pedidoService, times(1)).listarTodos();
    }

    @Test
    void testBuscarPedidoPorId() throws Exception {
        // Arrange
        when(pedidoService.buscarPorId(1L)).thenReturn(pedido);

        // Act & Assert
        mockMvc.perform(get("/api/v1/pedidos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value("ENVIADO"));

        verify(pedidoService, times(1)).buscarPorId(1L);
    }

    @Test
    void testBuscarPedidoPorIdNoExiste() throws Exception {
        // Arrange
        when(pedidoService.buscarPorId(999L))
                .thenThrow(new ResourceNotFoundException("No existe el pedido: 999"));

        // Act & Assert
        mockMvc.perform(get("/api/v1/pedidos/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(pedidoService, times(1)).buscarPorId(999L);
    }

    @Test
    void testActualizarEstado() throws Exception {
        // Arrange
        Pedido pedidoActualizado = new Pedido();
        pedidoActualizado.setId(1L);
        pedidoActualizado.setCliente("1");
        pedidoActualizado.setProducto("1");
        pedidoActualizado.setCantidad(2);
        pedidoActualizado.setPrecioTotal(2400.0);
        pedidoActualizado.setEstado("ENTREGADO");
        pedidoActualizado.setFechaCreacion(LocalDateTime.now());

        when(pedidoService.actualizarEstado(1L, "test@example.com", "ENTREGADO"))
                .thenReturn(pedidoActualizado);

        // Act & Assert
        mockMvc.perform(put("/api/v1/pedidos/1/estado")
                .param("correo", "test@example.com")
                .param("estado", "ENTREGADO")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value("ENTREGADO"));

        verify(pedidoService, times(1)).actualizarEstado(1L, "test@example.com", "ENTREGADO");
    }

    @Test
    void testActualizarEstadoPedidoNoExiste() throws Exception {
        // Arrange
        when(pedidoService.actualizarEstado(999L, "test@example.com", "ENTREGADO"))
                .thenThrow(new ResourceNotFoundException("No existe el pedido: 999"));

        // Act & Assert
        mockMvc.perform(put("/api/v1/pedidos/999/estado")
                .param("correo", "test@example.com")
                .param("estado", "ENTREGADO")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(pedidoService, times(1)).actualizarEstado(999L, "test@example.com", "ENTREGADO");
    }

    @Test
    void testActualizarEstadoConError() throws Exception {
        // Arrange
        when(pedidoService.actualizarEstado(anyLong(), any(), any()))
                .thenThrow(new RuntimeException("Error interno"));

        // Act & Assert
        mockMvc.perform(put("/api/v1/pedidos/1/estado")
                .param("correo", "test@example.com")
                .param("estado", "ENTREGADO")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(pedidoService, times(1)).actualizarEstado(1L, "test@example.com", "ENTREGADO");
    }
}
