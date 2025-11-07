package springWeb.demo.domain.Servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.CitaDTO;
import springWeb.demo.domain.Mapper.CitaMapper;
import springWeb.demo.domain.Modelos.Cita;
import springWeb.demo.domain.Modelos.EstadoCita;
import springWeb.demo.domain.Modelos.Mascota;
import springWeb.demo.domain.Modelos.Usuario;
import springWeb.demo.domain.Repositorios.CitaRepository;
import springWeb.demo.domain.Repositorios.MascotaRepository;
import springWeb.demo.domain.Repositorios.UsuarioRepository;
import springWeb.demo.domain.Servicios.interfaces.CitaService;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;
    private final MascotaRepository mascotaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public CitaDTO agendarCita(CitaDTO citaDTO) {
        Cita cita = citaMapper.toEntity(citaDTO);
        cita.setEstado(EstadoCita.PROGRAMADA);

        Mascota mascota = mascotaRepository.findById(citaDTO.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id " + citaDTO.getMascotaId()));

        // Al agendar, el veterinario puede ser pre-seleccionado
        Usuario veterinario = usuarioRepository.findById(citaDTO.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con id " + citaDTO.getVeterinarioId()));

        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);

        Cita nuevaCita = citaRepository.save(cita);
        return citaMapper.toDTO(nuevaCita);
    }

    @Override
    public List<CitaDTO> listarCitasPorVeterinario(Long veterinarioId) {
        return citaRepository.findByVeterinarioId(veterinarioId)
                .stream()
                .map(citaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDTO> listarCitasPorMascota(Long mascotaId) {
        return citaRepository.findByMascotaId(mascotaId)
                .stream()
                .map(citaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CitaDTO> obtenerCitaPorId(Long id) {
        return citaRepository.findById(id)
                .map(citaMapper::toDTO);
    }

    @Override
    public CitaDTO actualizarCita(Long citaId, CitaDTO citaDTO, UserDetails userDetails) {
        Cita citaExistente = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar: Cita no encontrada con id " + citaId));

        citaExistente.setFechaHora(citaDTO.getFechaHora());
        citaExistente.setMotivo(citaDTO.getMotivo());

        EstadoCita nuevoEstado = citaDTO.getEstado();
        if (nuevoEstado != null) {
            citaExistente.setEstado(nuevoEstado);

            if (nuevoEstado == EstadoCita.ACEPTADA) {
                String emailVeterinario = userDetails.getUsername();
                Usuario veterinarioAsignado = usuarioRepository.findByEmail(emailVeterinario)
                        .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con email: " + emailVeterinario));
                citaExistente.setVeterinario(veterinarioAsignado);
            }
        }

        Cita actualizada = citaRepository.save(citaExistente);
        return citaMapper.toDTO(actualizada);
    }

    @Override
    public void cancelarCita(Long id) {
        citaRepository.findById(id).ifPresent(cita -> {
            cita.setEstado(EstadoCita.CANCELADA);
            citaRepository.save(cita);
        });
    }

    @Override
    public List<CitaDTO> listarCitasPorEstado(String estado) {
        EstadoCita estadoEnum = EstadoCita.valueOf(estado.toUpperCase());
        return citaRepository.findByEstado(estadoEnum).stream()
                .map(citaMapper::toDTO)
                .collect(Collectors.toList());
    }
}