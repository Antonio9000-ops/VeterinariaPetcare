package springWeb.demo.domain.Servicios.interfaces;

import java.util.List;
import java.util.Optional;
import springWeb.demo.domain.Dto.HistoriaClinicaDTO;

public interface HistoriaClinicaService {

    HistoriaClinicaDTO registrarEntrada(HistoriaClinicaDTO dto);

    List<HistoriaClinicaDTO> listarPorMascota(Long mascotaId);

    Optional<HistoriaClinicaDTO> obtenerPorId(Long id);

    void eliminarEntrada(Long id);
}
