package ari.ms_catalogo.exception;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String mensaje;
    private String detalle;
    private Integer status;
    private LocalDateTime timestamp;
}