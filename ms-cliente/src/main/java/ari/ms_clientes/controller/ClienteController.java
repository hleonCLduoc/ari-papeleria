package ari.ms_clientes.controller;

import ari.ms_clientes.dto.ClienteRequest;
import ari.ms_clientes.model.Cliente;
import ari.ms_clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/clientes")
@Tag(
        name="Clientes",
        description="Operaciones relacionadas con la gestión de clientes"
)

public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    // para listar clientes
    @Operation(
            summary = "Listar clientes",
            description = "Obtiene todo los clientes registrados en el sistema"
    )
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        log.info("Iniciando lista de clientes...");
        List<Cliente> clientes = clienteService.findAll();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }
    //para crear cliente
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Cliente creado correctamente."),
        }
    )
    @Operation(
            summary = "Crear clientes",
            description = "Permite registrar un nuevo cliente en el sistema"
    )
    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody ClienteRequest request) {
        log.info("Creando nuevo cliente con Rut {}", request.getRut());

        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setRut(request.getRut());
        nuevoCliente.setNombre(request.getNombre());
        nuevoCliente.setApellido(request.getApellido());
        nuevoCliente.setCorreo(request.getCorreo());
        nuevoCliente.setTelefono(request.getTelefono());

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(nuevoCliente));
    }
    //para buscar cliente por id
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Cliente encontrado correctamente."),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado.")
         }
    )
    @Operation(
            summary = "Buscar clientes",
            description = "Permite buscar un cliente por identificador"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@Parameter (description = "Id del cliente que se desea buscar", example = "1") @PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }
    //para actualizar
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Cliente actualizado correctamente."),
    }
    )
    @Operation(
            summary = "Editar/actualizar clientes",
            description = "Actualizar la información de un cliente existente"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@Parameter (description = "Id del cliente que desea actualizar", example = "1") @PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
        Cliente actual = clienteService.findById(id);

        actual.setRut(request.getRut());
        actual.setNombre(request.getNombre());
        actual.setApellido(request.getApellido());
        actual.setCorreo(request.getCorreo());
        actual.setTelefono(request.getTelefono());

        return ResponseEntity.ok(clienteService.save(actual));
    }
    //para borrar un cliente
    @ApiResponses( value = {
            @ApiResponse( responseCode = "204", description = "Operación exitosa."),
    }
    )
    @Operation(
            summary = "Eliminar clientes",
            description = "Elimina un cliente con su identificador"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@Parameter (description = "Id del cliente que desea eliminar", example = "1") @PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}