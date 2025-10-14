package springWeb.demo.domain.Dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacunaDTO {

    private Long id;
    private String nombre;
    private LocalDate fechaAplicacion;

    private Long mascotaId;
    private String mascotaNombre;

    private Long veterinarioId;
    private String veterinarioNombre;
}
