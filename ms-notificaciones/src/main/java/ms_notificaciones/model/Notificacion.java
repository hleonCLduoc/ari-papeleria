package ms_notificaciones.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String correoDestino;

    @Column(nullable = false)
    private String asunto;

    @Column(nullable = false, length = 500)
    private String mensaje;

    private LocalDateTime fechaEnvio;
}