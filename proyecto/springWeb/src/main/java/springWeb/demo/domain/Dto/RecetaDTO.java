package springWeb.demo.domain.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecetaDTO {
    private Long id;
    private String medicamento;
    private String dosis;
    private String duracion;
    private Long historiaClinicaId;
}