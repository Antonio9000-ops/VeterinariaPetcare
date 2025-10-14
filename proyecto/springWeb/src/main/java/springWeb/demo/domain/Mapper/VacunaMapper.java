package springWeb.demo.domain.Mapper;

import org.springframework.stereotype.Component;
import springWeb.demo.domain.Dto.VacunaDTO;
import springWeb.demo.domain.Modelos.Vacuna;

@Component
public class VacunaMapper {

    public static VacunaDTO toDTO(Vacuna vacuna) {
        if (vacuna == null) return null;

        return VacunaDTO.builder()
                .id(vacuna.getId())
                .nombre(vacuna.getNombre())
                .fechaAplicacion(vacuna.getFechaAplicacion())
                .mascotaId(vacuna.getMascota() != null ? vacuna.getMascota().getId() : null)
                .mascotaNombre(vacuna.getMascota() != null ? vacuna.getMascota().getNombre() : null)
                .veterinarioId(vacuna.getVeterinario() != null ? vacuna.getVeterinario().getId() : null)
                .veterinarioNombre(vacuna.getVeterinario() != null ? vacuna.getVeterinario().getNombre() : null)
                .build();
    }

    public static Vacuna toEntity(VacunaDTO dto) {
        if (dto == null) return null;

        return Vacuna.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .fechaAplicacion(dto.getFechaAplicacion())
                // Relaciones se llenan en el service
                .mascota(null)
                .veterinario(null)
                .build();
    }
}
