package com.aripapeleria.ms_pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El cliente es obligatorio")
    private String cliente;

    @NotBlank(message = "El producto es obligatorio")
    private String producto;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    @NotNull(message = "El precio total es obligatorio")
    @Positive(message = "El precio total debe ser mayor a cero")
    private Double precioTotal;

    private String estado = "Pendiente de Pago";

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}