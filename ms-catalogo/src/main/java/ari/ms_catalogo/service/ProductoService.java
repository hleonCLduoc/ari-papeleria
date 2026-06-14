package ari.ms_catalogo.service;

import ari.ms_catalogo.model.Producto;
import ari.ms_catalogo.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Transactional
@Service
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);

    @Autowired
    private ProductoRepository productoRepository;

    //listar prodcutos
    public List <Producto> findAll(){
        log.info("consultando todos los productos del catalogo");//log informativo
        return productoRepository.findAll();
    }

    //guardar
    public Producto save(Producto producto){
        log.info("Guardando producto: {}", producto.getNombre()); //log informativo
        return productoRepository.save(producto);
    }

    //regla de negocio stock
    public Producto reducirStock(Long id, Integer cantidadComprada){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado."));
        log.error("Error: producto con id{} no encontrado", id );//log error
        if (producto.getStock()< cantidadComprada){
            throw new RuntimeException("No hay stock suficiente para esta compra.");
        }
        producto.setStock(producto.getStock()- cantidadComprada);
        log.info("Stock reducido para el producto {} .Nuevo stock: {}", id, producto.getStock());

        //indicar agotado si llega a cero
        if (producto.getStock() == 0){
            producto.setEstado("Agotado.");
            log.info("El producto {} se ha agotado", id);
        }
        return productoRepository.save(producto);
    }

    //buscar por id
    public Producto findById(Long id){
        return productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado."));
    }


        //elinminar
    public void delete(Long id){
        productoRepository.deleteById(id);
        log.info("Producto con id {} se ha eliminado del catalogo", id);
    }

}