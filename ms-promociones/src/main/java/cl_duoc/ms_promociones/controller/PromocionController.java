package cl_duoc.ms_promociones.controller;

import cl_duoc.ms_promociones.model.Promocion;
import cl_duoc.ms_promociones.repository.PromocionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/promociones")
public class PromocionController {

    @Autowired
    private PromocionRepository promocionRepository;

    @PostMapping
    public ResponseEntity<Promocion> crearPromocion(@Valid @RequestBody Promocion promocion) {
        Promocion nuevaPromocion = promocionRepository.save(promocion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPromocion);
    }

    // NUEVO ENDPOINT: Buscar promoción por el texto del código
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Promocion> buscarPorCodigo(@PathVariable String codigo) {
        Promocion promocion = promocionRepository.findByCodigo(codigo);
        if (promocion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(promocion);
    }
}