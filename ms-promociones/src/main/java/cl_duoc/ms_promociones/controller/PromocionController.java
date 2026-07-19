package cl_duoc.ms_promociones.controller;

import cl_duoc.ms_promociones.model.Promocion;
import cl_duoc.ms_promociones.repository.PromocionRepository;
import cl_duoc.ms_promociones.service.PromocionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/promociones")
@Tag(name = "Promociones", description = "Endpoints para la gestión de códigos de descuento")
public class PromocionController {



    @Autowired
    private PromocionRepository promocionRepository;

    @Autowired
    private PromocionService promocionService;


    @PostMapping
    @Operation(summary = "Crear promoción", description = "Guarda un nuevo código de descuento en la BD.")
    @ApiResponse(responseCode = "201", description = "Promoción creada")
    public ResponseEntity<Promocion> crearPromocion(@Valid @RequestBody Promocion promocion) {
        Promocion nuevaPromocion = promocionRepository.save(promocion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPromocion);
    }

    @GetMapping("/codigo/{codigo}")
    @Operation(summary = "Buscar por código", description = "Obtiene los detalles buscando por texto (ej: LIBRERIA20).")
    @ApiResponse(responseCode = "200", description = "Promoción encontrada")
    @ApiResponse(responseCode = "404", description = "Promoción no encontrada")
    public ResponseEntity<Promocion> buscarPorCodigo(@PathVariable String codigo) {
        Promocion promocion = promocionRepository.findByCodigo(codigo);
        if (promocion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(promocion);
    }



    @GetMapping
    @Operation(summary = "Obtener todas las promociones", description = "Devuelve una lista con todas las promociones registradas en la BD.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito")
    public ResponseEntity<List<Promocion>> obtenerTodas() {
        List<Promocion> lista = promocionService.findAll();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar promoción", description = "Actualiza los datos de una promoción existente por su ID.")
    @ApiResponse(responseCode = "200", description = "Promoción actualizada")
    @ApiResponse(responseCode = "404", description = "Promoción no encontrada")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Promocion promocion) {
        Promocion promoActualizada = promocionService.actualizar(id, promocion);
        if (promoActualizada != null) {
            return ResponseEntity.ok(promoActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar promoción", description = "Elimina una promoción de la base de datos usando su ID.")
    @ApiResponse(responseCode = "200", description = "Promoción eliminada")
    @ApiResponse(responseCode = "404", description = "Promoción no encontrada")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Promocion existente = promocionService.buscarPorId(id);
        if (existente != null) {
            promocionService.eliminar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}