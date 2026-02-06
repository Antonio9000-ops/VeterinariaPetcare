package springWeb.demo.domain.Mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import springWeb.demo.domain.Dto.MascotaDTO;
import springWeb.demo.domain.Modelos.Cita;
import springWeb.demo.domain.Modelos.HistoriaClinica;
import springWeb.demo.domain.Modelos.Mascota;
import springWeb.demo.domain.Modelos.Usuario;
import springWeb.demo.domain.Modelos.Vacuna;

@Component
public class MascotaMapper {

        public MascotaDTO toDTO(Mascota mascota) {
                if (mascota == null)
                        return null;

                return MascotaDTO.builder()
                                .id(mascota.getId())
                                .nombre(mascota.getNombre())
                                .especie(mascota.getEspecie())
                                .raza(mascota.getRaza())
                                .fechaNacimiento(mascota.getFechaNacimiento())
                                // Cambios aquí:
                                .usuarioId(mascota.getUsuario() != null ? mascota.getUsuario().getId() : null)
                                .usuarioNombre(mascota.getUsuario() != null ? mascota.getUsuario().getNombre() : null)
                                .citasIds(mascota.getCitas() != null
                                                ? mascota.getCitas().stream().map(Cita::getId).toList()
                                                : List.of())
                                .historiaClinicaIds(mascota.getHistoriaClinica() != null
                                                ? mascota.getHistoriaClinica().stream().map(HistoriaClinica::getId)
                                                                .toList()
                                                : List.of())
                                .vacunasIds(mascota.getVacunas() != null
                                                ? mascota.getVacunas().stream().map(Vacuna::getId).toList()
                                                : List.of())
                                .build();
        }

        public Mascota toEntity(MascotaDTO dto) {
                if (dto == null)
                        return null;

                Usuario usuario = null;
                if (dto.getUsuarioId() != null) {
                        usuario = new Usuario();
                        usuario.setId(dto.getUsuarioId());
                }

                return Mascota.builder()
                                .id(dto.getId())
                                .nombre(dto.getNombre())
                                .especie(dto.getEspecie())
                                .raza(dto.getRaza())
                                .fechaNacimiento(dto.getFechaNacimiento())
                                .usuario(usuario)
                                .citas(null)
                                .historiaClinica(null)
                                .vacunas(null)
                                .build();
        }
}
