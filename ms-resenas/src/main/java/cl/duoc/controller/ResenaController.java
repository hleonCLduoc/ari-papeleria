package cl.duoc.controller;

import cl.duoc.model.Resena;
import cl.duoc.service.ResenaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    // Endpoint usando @Valid para aplicar validaciones JSR 380
    @PostMapping
    public ResponseEntity<Resena> crearResena(@Valid @RequestBody Resena resena) {
        Resena nuevaResena = resenaService.guardarResena(resena);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaResena);
    }

    // Endpoint para ver qué opinan de un producto
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Resena>> obtenerPorProducto(@PathVariable String productoId) {
        List<Resena> resenas = resenaService.obtenerResenasPorProducto(productoId);
        if(resenas.isEmpty()){
            return ResponseEntity.noContent().build(); // 204 si aún no hay opiniones
        }
        return ResponseEntity.ok(resenas);
    }
}