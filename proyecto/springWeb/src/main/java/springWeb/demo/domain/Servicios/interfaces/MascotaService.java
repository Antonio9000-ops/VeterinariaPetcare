
package springWeb.demo.domain.Servicios.interfaces;

import java.util.List;
import java.util.Optional;
import springWeb.demo.domain.Dto.MascotaDTO;

public interface MascotaService {

    MascotaDTO registrarMascota(MascotaDTO mascotaDTO);

    List<MascotaDTO> listarMascotasPorDueño(Long duenoId);

    Optional<MascotaDTO> obtenerMascotaPorId(Long id);

    MascotaDTO actualizarMascota(MascotaDTO mascotaDTO);

    void eliminarMascota(Long id);
}
