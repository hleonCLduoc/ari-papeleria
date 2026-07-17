package com.aripapeleria.ms_pedidos.controller;

import com.aripapeleria.ms_pedidos.model.Pedido;
import com.aripapeleria.ms_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 1. Nuevos imports obligatorios para Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
// 2. @Tag agrupa tu controlador en la interfaz web de Swagger
@Tag(name = "Pedidos", description = "Operaciones relacionadas con la gestión de pedidos del E-commerce")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // 1. Listar todos los pedidos
    @Operation(summary = "Listar todos los pedidos", description = "Obtiene una lista completa de todos los pedidos registrados en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay pedidos registrados en el sistema", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 si está vacío
        }
        return ResponseEntity.ok(pedidos); // Retorna 200 OK
    }

    // 2. Buscar un pedido por su ID
    @Operation(summary = "Buscar un pedido por ID", description = "Busca un pedido específico utilizando su código numérico de identificación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "El pedido no existe en la base de datos", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(
            // 3. @Parameter detalla qué significa la variable {id} de la URL
            @Parameter(description = "ID numérico del pedido a buscar", required = true)
            @PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.buscarPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no existe
        }
    }

    // 3. Actualizar estado
    @Operation(summary = "Actualizar el estado de un pedido", description = "Modifica el estado de un pedido existente validando el correo del usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del pedido actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "El pedido no existe o los datos ingresados son incorrectos", content = @Content)
    })
    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(
            @Parameter(description = "ID numérico del pedido a actualizar", required = true) @PathVariable Long id,
            @Parameter(description = "Correo del usuario para validación", required = true) @RequestParam String correo,
            @Parameter(description = "Nuevo estado a asignar al pedido", required = true) @RequestParam String estado) {
        try {
            Pedido pedidoActualizado = pedidoService.actualizarEstado(id, correo, estado);
            return ResponseEntity.ok(pedidoActualizado);

        } catch (Exception e) {
            // Si el pedido no existe, retorna 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}