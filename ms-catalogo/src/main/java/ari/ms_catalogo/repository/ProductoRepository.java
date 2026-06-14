package ari.ms_catalogo.repository;

import ari.ms_catalogo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
