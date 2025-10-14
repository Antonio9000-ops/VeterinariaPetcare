
package springWeb.demo.domain.Servicios;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.CitaDTO;
import springWeb.demo.domain.Mapper.CitaMapper;
import springWeb.demo.domain.Modelos.Cita;
import springWeb.demo.domain.Modelos.EstadoCita;
import springWeb.demo.domain.Repositorios.CitaRepository;
import springWeb.demo.domain.Servicios.interfaces.CitaService;


@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;

    @Override
    public CitaDTO agendarCita(CitaDTO citaDTO) {
        // Convertir DTO a entidad
        Cita cita = citaMapper.toEntity(citaDTO);
        cita.setEstado(EstadoCita.PROGRAMADA);

        // Guardar en BD
        Cita nuevaCita = citaRepository.save(cita);

        // Convertir de vuelta a DTO
        return citaMapper.toDTO(nuevaCita);
    }

    @Override
    public List<CitaDTO> listarCitasPorVeterinario(Long veterinarioId) {
        return citaRepository.findByVeterinarioId(veterinarioId)
                .stream()
                .map(citaMapper::toDTO)
                .toList();
    }

    @Override
    public List<CitaDTO> listarCitasPorMascota(Long mascotaId) {
        return citaRepository.findByMascotaId(mascotaId)
                .stream()
                .map(citaMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<CitaDTO> obtenerCitaPorId(Long id) {
        return citaRepository.findById(id)
                .map(citaMapper::toDTO);
    }

    @Override
    public CitaDTO actualizarCita(CitaDTO citaDTO) {
        Cita cita = citaMapper.toEntity(citaDTO);
        Cita actualizada = citaRepository.save(cita);
        return citaMapper.toDTO(actualizada);
    }

    @Override
    public void cancelarCita(Long id) {
        citaRepository.findById(id).ifPresent(cita -> {
            cita.setEstado(EstadoCita.CANCELADA);
            citaRepository.save(cita);
        });
    }
}
