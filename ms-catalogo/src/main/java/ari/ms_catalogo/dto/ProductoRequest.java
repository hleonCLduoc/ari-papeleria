package ari.ms_catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data

public class ProductoRequest {

    @NotBlank(message = " El SKU es obligatorio.")
    private String sku;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message =  "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private double precio;

    @NotNull(message = "El Stock es obligatorio")
    @Positive(message = "El Stock debe ser mayor a 0")
    private Integer stock;
}
