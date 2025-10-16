package springWeb.demo.domain.Servicios.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import springWeb.demo.domain.Dto.CitaDTO;

public interface CitaService {
    CitaDTO agendarCita(CitaDTO citaDTO);
    List<CitaDTO> listarCitasPorVeterinario(Long veterinarioId);
    List<CitaDTO> listarCitasPorMascota(Long mascotaId);
    Optional<CitaDTO> obtenerCitaPorId(Long id);
    void cancelarCita(Long id);
    List<CitaDTO> listarCitasPorEstado(String estado);

    // Firma del metodo actualizada para incluir el ID y los detalles del usuario
    CitaDTO actualizarCita(Long citaId, CitaDTO citaDTO, UserDetails userDetails);
}