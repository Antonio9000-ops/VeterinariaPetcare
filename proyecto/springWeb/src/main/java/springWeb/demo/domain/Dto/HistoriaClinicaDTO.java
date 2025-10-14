package springWeb.demo.domain.Dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriaClinicaDTO {

    private Long id;
    private LocalDateTime fecha;
    private String descripcion;

    private Long mascotaId;
    private String mascotaNombre;

    private Long veterinarioId;
    private String veterinarioNombre;
}
