package springWeb.demo.domain.Dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class HistoriaClinicaDTO {
    private Long id;
    private LocalDateTime fecha;
    private String descripcion;
    private Long mascotaId;
    private String mascotaNombre;
    private Long veterinarioId;
    private String veterinarioNombre;

    
    private List<TratamientoDTO> tratamientos;
    private List<RecetaDTO> recetas;
}