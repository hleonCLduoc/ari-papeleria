package ari.ms_clientes.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;


@Data

public class ClienteRequest {

    @NotBlank(message="El RUT es obligatorio.")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotBlank(message= "El apellido es obligatorio.")
    private String apellido;

    @NotBlank(message = "El correo es obligatorio.")
    private String correo;

    @NotBlank(message = "El telefono es obligatorio.")
    private String telefono;

}
