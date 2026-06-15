package cl.duoc.service;

import cl.duoc.model.Resena;
import cl.duoc.repository.ResenaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {

    private static final Logger log = LoggerFactory.getLogger(ResenaService.class);

    @Autowired
    private ResenaRepository resenaRepository;

    public Resena guardarResena(Resena resena) {
        log.info("Validando si el cliente {} ya reseñó el producto {}", resena.getClienteId(), resena.getProductoId());

        // REGLA DE NEGOCIO: Bloquear reseñas duplicadas
        if (resenaRepository.existsByProductoIdAndClienteId(resena.getProductoId(), resena.getClienteId())) {
            log.warn("Bloqueo de seguridad: El cliente ya reseñó este producto.");
            throw new IllegalArgumentException("Error: No puedes dejar más de una reseña para el mismo producto.");
        }

        log.info("Guardando nueva reseña exitosamente en db_resenas");
        return resenaRepository.save(resena);
    }

    public List<Resena> obtenerResenasPorProducto(String productoId) {
        log.info("Consultando reseñas del producto ID: {}", productoId);
        return resenaRepository.findByProductoId(productoId);
    }
}