package springWeb.demo.domain.Servicios.interfaces;

import java.util.List;
import java.util.Optional;
import springWeb.demo.domain.Dto.HistoriaClinicaDTO;
import springWeb.demo.domain.Dto.RecetaDTO;
import springWeb.demo.domain.Dto.TratamientoDTO;

public interface HistoriaClinicaService {

    HistoriaClinicaDTO registrarEntrada(HistoriaClinicaDTO dto);

    List<HistoriaClinicaDTO> listarPorMascota(Long mascotaId);

    Optional<HistoriaClinicaDTO> obtenerPorId(Long id);

    void eliminarEntrada(Long id);
    HistoriaClinicaDTO guardarHistoria(HistoriaClinicaDTO historiaClinicaDTO);

    List<TratamientoDTO> listarTratamientosPorMascota(Long mascotaId);
    List<RecetaDTO> listarRecetasPorMascota(Long mascotaId);
}
