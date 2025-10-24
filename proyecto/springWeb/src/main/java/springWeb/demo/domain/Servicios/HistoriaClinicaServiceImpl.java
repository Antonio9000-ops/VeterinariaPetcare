
package springWeb.demo.domain.Servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.HistoriaClinicaDTO;
import springWeb.demo.domain.Mapper.HistoriaClinicaMapper;
import springWeb.demo.domain.Modelos.HistoriaClinica;
import springWeb.demo.domain.Repositorios.HistoriaClinicaRepository;
import springWeb.demo.domain.Servicios.interfaces.HistoriaClinicaService;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;
    //no se necesita la intancia del mapper si todos los demas metodos son estaticos

    @Override
    public HistoriaClinicaDTO registrarEntrada(HistoriaClinicaDTO dto) {
        // Convertir DTO a entidad
        HistoriaClinica historia = HistoriaClinicaMapper.toEntity(dto); 
        historia.setFecha(LocalDateTime.now());

        // Guardar en BD
        HistoriaClinica nuevaEntrada = historiaClinicaRepository.save(historia);

        // Convertir de vuelta a DTO
        return HistoriaClinicaMapper.toDTO(nuevaEntrada);
    }

    @Override
    public List<HistoriaClinicaDTO> listarPorMascota(Long mascotaId) {
        return historiaClinicaRepository.findByMascotaId(mascotaId)
                .stream()
                .map(HistoriaClinicaMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<HistoriaClinicaDTO> obtenerPorId(Long id) {
        return historiaClinicaRepository.findById(id)
                .map(HistoriaClinicaMapper::toDTO);
    }

    @Override
    public void eliminarEntrada(Long id) {
        historiaClinicaRepository.deleteById(id);
    }
}
