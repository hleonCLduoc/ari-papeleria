package cl_duoc.ms_promociones.repository;

import cl_duoc.ms_promociones.model.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Long> {

    // Busca exactamente por la columna codigo en la base de datos
    Promocion findByCodigo(String codigo);
}