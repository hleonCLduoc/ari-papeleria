package ari.ms_catalogo.dto;

import lombok.Data;

@Data

public class ProductoDTO {
    private Long id;
    private String sku;
    private String nombre;
    private double precio;
    private Integer stock;
    private String estado;
}
