package com.aripapeleria.ms_pedidos.controller;

import com.aripapeleria.ms_pedidos.model.Pedido;
import com.aripapeleria.ms_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // 1. Listar todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 si está vacío
        }
        return ResponseEntity.ok(pedidos); // Retorna 200 OK
    }

    // 2. Buscar un pedido por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.buscarPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no existe
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String correo,
            @RequestParam String estado) {
        try {
            Pedido pedidoActualizado = pedidoService.actualizarEstado(id, correo, estado);
            return ResponseEntity.ok(pedidoActualizado);

        } catch (Exception e) {
            // Si el pedido no existe, retorna 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}