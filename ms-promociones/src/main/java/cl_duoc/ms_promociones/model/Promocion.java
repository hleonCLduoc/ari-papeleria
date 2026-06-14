package cl_duoc.ms_promociones.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promociones")
@Data
@NoArgsConstructor
@AllArgsConstructor



public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "el codigo de la promocion no puede estar vacio.")
    @Size(min= 3, max=20, message = "El codigo debe tener entre 3 y 20 Caracteres")
    @Column(unique = true, nullable = false, length = 20)
    private String codigo;

    @NotBlank (message = "La descripcion es obligatoria")
    @Column (nullable = false, length = 100 )
    private String nombre;

    @NotNull (message = "El porcentaje de descuento no puede estar vacio")
    @Positive(message= "El porcentaje de descuento debe ser mayor a cero")
    @Column (nullable = false, length = 2)
    private Double porcentaje;

    @NotBlank
    @Column (nullable = false, length = 20)
    private String estado;


}
