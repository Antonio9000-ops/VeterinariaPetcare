
package springWeb.demo.domain.Servicios.interfaces;

import java.util.List;
import java.util.Optional;
import springWeb.demo.domain.Dto.VacunaDTO;

public interface VacunaService {
    VacunaDTO registrarVacuna(VacunaDTO vacunaDTO);
    List<VacunaDTO> listarVacunasPorMascota(Long mascotaId);
    Optional<VacunaDTO> obtenerVacunaPorId(Long id);
    void eliminarVacuna(Long id);
}
