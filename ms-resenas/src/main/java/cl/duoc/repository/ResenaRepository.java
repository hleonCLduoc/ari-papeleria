package cl.duoc.repository;

import cl.duoc.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {

    List<Resena> findByProductoId(String productoId);

    //Verifica si ya existe una reseña de ese cliente para ese producto
    boolean existsByProductoIdAndClienteId(String productoId, String clienteId);
}