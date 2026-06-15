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
        log.info("Guardando nueva reseña para el producto ID: {}", resena.getProductoId());
        return resenaRepository.save(resena);
    }

    public List<Resena> obtenerResenasPorProducto(String productoId) {
        log.info("Consultando reseñas del producto ID: {}", productoId);
        return resenaRepository.findByProductoId(productoId);
    }
}