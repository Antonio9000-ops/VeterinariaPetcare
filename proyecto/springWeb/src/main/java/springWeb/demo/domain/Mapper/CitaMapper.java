package springWeb.demo.domain.Mapper;

import org.springframework.stereotype.Component;
import springWeb.demo.domain.Dto.CitaDTO;
import springWeb.demo.domain.Modelos.Cita;

@Component
public class CitaMapper {

    public CitaDTO toDTO(Cita cita) {
        if (cita == null) return null;

        return CitaDTO.builder()
                .id(cita.getId())
                .fechaHora(cita.getFechaHora())
                .motivo(cita.getMotivo())
                .estado(cita.getEstado())
                .mascotaId(cita.getMascota() != null ? cita.getMascota().getId() : null)
                .mascotaNombre(cita.getMascota() != null ? cita.getMascota().getNombre() : null)
                .veterinarioId(cita.getVeterinario() != null ? cita.getVeterinario().getId() : null)
                .veterinarioNombre(cita.getVeterinario() != null ? cita.getVeterinario().getNombre() : null)
                .build();
    }

    public Cita toEntity(CitaDTO dto) {
        if (dto == null) return null;

        return Cita.builder()
                .id(dto.getId())
                .fechaHora(dto.getFechaHora())
                .motivo(dto.getMotivo())
                .estado(dto.getEstado())
                // Relaciones se asignan después en el Service
                .mascota(null)
                .veterinario(null)
                .build();
    }
}
