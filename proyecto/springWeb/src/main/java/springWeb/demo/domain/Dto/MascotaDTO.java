package springWeb.demo.domain.Dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MascotaDTO {

    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private LocalDate fechaNacimiento;

    // 👇 en lugar de exponer todo el Usuario dueño
    private Long duenoId;
    private String duenoNombre;

    // 👇 no mandamos toda la cita, historia o vacuna, solo lo esencial
    private List<Long> citasIds;
    private List<Long> historiaClinicaIds;
    private List<Long> vacunasIds;
}
