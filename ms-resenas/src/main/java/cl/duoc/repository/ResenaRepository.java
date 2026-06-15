package cl.duoc.repository;

import cl.duoc.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
    // Consulta personalizada para listar opiniones por producto
    List<Resena> findByProductoId(String productoId);
}