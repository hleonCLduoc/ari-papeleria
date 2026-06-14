package ari.ms_catalogo.controller;

import ari.ms_catalogo.dto.ProductoRequest;
import ari.ms_catalogo.model.Producto;
import ari.ms_catalogo.service.ProductoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/productos")

public class ProductoController {
    @Autowired

    private ProductoService productoService;
//listar productos
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        log.info("Iniciando lista de productos del catalogo");
        List<Producto> productos = productoService.findAll();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);// da 200 ok


    }
    // crearproducto
    @PostMapping
    public ResponseEntity<Producto> crear( @Valid @RequestBody ProductoRequest request) {
        log.info("Iniciando creacion del producto: Nuevo producto con SKU {}", request.getSku());

        Producto nuevoProducto = new Producto();
        nuevoProducto.setSku(request.getSku());
        nuevoProducto.setNombre(request.getNombre());
        nuevoProducto.setPrecio(request.getPrecio());
        nuevoProducto.setStock(request.getStock());

        nuevoProducto.setEstado(request.getStock() == 0 ? "Agotado": "Disponible");

        Producto producto = productoService.save(nuevoProducto);

        return ResponseEntity.status(HttpStatus.CREATED).body(producto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable Long id) {
        try {
            Producto producto = productoService.findById(id);
            return ResponseEntity.ok(producto);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        // GlobalExceptionHandler atrapará cualquier falla automáticamente
        Producto prodActual = productoService.findById(id);

        prodActual.setSku(producto.getSku());
        prodActual.setNombre(producto.getNombre());
        prodActual.setPrecio(producto.getPrecio());
        prodActual.setStock(producto.getStock());
        prodActual.setEstado(producto.getEstado());

        return ResponseEntity.ok(productoService.save(prodActual));
    }

    //pa borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            productoService.delete(id);
            return ResponseEntity.noContent().build();//200
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();//400
        }


    }

}