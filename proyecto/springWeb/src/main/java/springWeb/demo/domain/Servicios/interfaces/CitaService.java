
package springWeb.demo.domain.Servicios.interfaces;

import java.util.List;
import java.util.Optional;
import springWeb.demo.domain.Dto.CitaDTO;


public interface CitaService {
    CitaDTO agendarCita(CitaDTO citaDTO);
    List<CitaDTO> listarCitasPorVeterinario(Long veterinarioId);
    List<CitaDTO> listarCitasPorMascota(Long mascotaId);
    Optional<CitaDTO> obtenerCitaPorId(Long id);
    CitaDTO actualizarCita(CitaDTO citaDTO);
    void cancelarCita(Long id);
}
