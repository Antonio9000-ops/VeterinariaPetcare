package springWeb.demo.domain.Dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springWeb.demo.domain.Modelos.EstadoCita;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaDTO {

    private Long id;
    private LocalDateTime fechaHora;
    private String motivo;
    private EstadoCita estado;

    // 👇 en lugar de exponer toda la entidad Mascota y Usuario
    private Long mascotaId;
    private String mascotaNombre;

    private Long veterinarioId;
    private String veterinarioNombre;
}
