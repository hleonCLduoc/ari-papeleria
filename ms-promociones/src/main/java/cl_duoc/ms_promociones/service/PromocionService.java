package cl_duoc.ms_promociones.service;

import cl_duoc.ms_promociones.model.Promocion;
import cl_duoc.ms_promociones.repository.PromocionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PromocionService {

    private static final Logger log = LoggerFactory.getLogger(PromocionService.class);

    @Autowired
    private PromocionRepository promocionRepository;

    public List<Promocion> findAll() {
        log.info("Consultando la lista completa todas las promociones");
        return promocionRepository.findAll();
    }

    public Promocion buscarPorId(Long id) {
        log.info("Consultando la promocion id {}", id);
        return promocionRepository.findById(id).orElse(null);
    }

    public Promocion guardar(Promocion promocion) {
        log.info("Guardando la promocion {}", promocion);
        return promocionRepository.save(promocion);
    }

    public void eliminar(Long id) {
        log.info("Eliminando la promocion con id {}", id);
        promocionRepository.deleteById(id);
    }

    // --- PARA ACTUALIZAR ---

    public Promocion actualizar(Long id, Promocion detallesPromocion) {
        log.info("Actualizando la promocion con id {}", id);


        Promocion promoExistente = buscarPorId(id);

        if (promoExistente != null) {
            // Actualizo solo los datos
            promoExistente.setCodigo(detallesPromocion.getCodigo());
            promoExistente.setNombre(detallesPromocion.getNombre());
            promoExistente.setPorcentaje(detallesPromocion.getPorcentaje());
            promoExistente.setEstado(detallesPromocion.getEstado());

            // Guardo los cambios
            return guardar(promoExistente);
        }
        return null; // Retorna nulo si no encuentra el idd
    }
}