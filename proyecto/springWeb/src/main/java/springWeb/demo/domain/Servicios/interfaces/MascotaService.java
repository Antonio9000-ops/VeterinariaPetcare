
package springWeb.demo.domain.Servicios.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import springWeb.demo.domain.Dto.MascotaDTO;
import springWeb.demo.domain.Modelos.Mascota;

public interface MascotaService {
    List<MascotaDTO> listarTodasLasMascotas();

    MascotaDTO registrarMascota(MascotaDTO mascotaDTO);

    List<MascotaDTO> listarMascotasPorDueño(Long duenoId);

    Optional<MascotaDTO> obtenerMascotaPorId(Long id);

    MascotaDTO actualizarMascota(MascotaDTO mascotaDTO);

    void eliminarMascota(Long id);

    List<Mascota> findMascotasConCitaAceptada();

}
