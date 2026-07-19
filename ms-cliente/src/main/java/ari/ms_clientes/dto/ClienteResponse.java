package ari.ms_clientes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Schema(description = "DTO de respuesta con la información del cliente")
public class ClienteResponse {

    @Schema(description = "Rut del cliente",
            example = "12345678-9")
    private String rut;

    @Schema(description = "Nombre del cliente",
            example = "Juan")
    private String nombre;

    @Schema(description = "Apellido del cliente",
            example = "Pérez")
    private String apellido;

    @Schema(description = "Correo del cliente",
            example = "juan.perez@aripapeleria.cl")
    private String correo;

    @Schema(description = "Telefono del cliente",
            example = "911223344")
    private String telefono;

}
