package springWeb.demo.domain.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class TratamientoDTO {
    private Long id;
    private String descripcion;
    private LocalDate fecha; 
    private Long historiaClinicaId;
}