package cl.duoc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "resenas")
@Data
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El ID del producto es obligatorio")
    @Column(nullable = false)
    private String productoId;

    @NotBlank(message = "El ID del cliente es obligatorio")
    @Column(nullable = false)
    private String clienteId;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1 estrella")
    @Max(value = 5, message = "La calificación máxima es 5 estrellas")
    @Column(nullable = false)
    private Integer calificacion;

    @NotBlank(message = "El comentario no puede estar vacío")
    @Size(max = 500, message = "El comentario no puede superar los 500 caracteres")
    @Column(nullable = false, length = 500)
    private String comentario;

    // Se asigna la fecha automáticamente
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}