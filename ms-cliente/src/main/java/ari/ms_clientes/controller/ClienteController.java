package ari.ms_clientes.controller;

import ari.ms_clientes.dto.ClienteRequest;
import ari.ms_clientes.model.Cliente;
import ari.ms_clientes.service.ClienteService;
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
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    // pa listar clientes
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
    //pára buscar cliente por id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }
    //para actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
        Cliente actual = clienteService.findById(id);

        actual.setRut(request.getRut());
        actual.setNombre(request.getNombre());
        actual.setApellido(request.getApellido());
        actual.setCorreo(request.getCorreo());
        actual.setTelefono(request.getTelefono());

        return ResponseEntity.ok(clienteService.save(actual));
    }
    //para borrar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}