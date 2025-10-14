package springWeb.demo.domain.Mapper;

import org.springframework.stereotype.Component;
import springWeb.demo.domain.Dto.HistoriaClinicaDTO;
import springWeb.demo.domain.Modelos.HistoriaClinica;

@Component
public class HistoriaClinicaMapper {

    public static HistoriaClinicaDTO toDTO(HistoriaClinica historia) {
        if (historia == null) return null;

        return HistoriaClinicaDTO.builder()
                .id(historia.getId())
                .fecha(historia.getFecha())
                .descripcion(historia.getDescripcion())
                .mascotaId(historia.getMascota() != null ? historia.getMascota().getId() : null)
                .mascotaNombre(historia.getMascota() != null ? historia.getMascota().getNombre() : null)
                .veterinarioId(historia.getVeterinario() != null ? historia.getVeterinario().getId() : null)
                .veterinarioNombre(historia.getVeterinario() != null ? historia.getVeterinario().getNombre() : null)
                .build();
    }

    public static HistoriaClinica toEntity(HistoriaClinicaDTO dto) {
        if (dto == null) return null;

        return HistoriaClinica.builder()
                .id(dto.getId())
                .fecha(dto.getFecha())
                .descripcion(dto.getDescripcion())
                // Relaciones se pueden llenar después en el Service
                .mascota(null)
                .veterinario(null)
                .build();
    }
}
