package springWeb.demo.domain.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.VacunaDTO;
import springWeb.demo.domain.Mapper.VacunaMapper;
import springWeb.demo.domain.Modelos.Vacuna;
import springWeb.demo.domain.Repositorios.VacunaRepository;
import springWeb.demo.domain.Servicios.interfaces.VacunaService;

@Service
@RequiredArgsConstructor
public class VacunaServiceImpl implements VacunaService {

    private final VacunaRepository vacunaRepository;
    

    @Override
    public VacunaDTO registrarVacuna(VacunaDTO dto) {
        //llama al metodo de forma estatica
        Vacuna vacuna = VacunaMapper.toEntity(dto);

        if (vacuna.getFechaAplicacion() == null) {
            vacuna.setFechaAplicacion(LocalDate.now());
        }

        Vacuna nuevaVacuna = vacunaRepository.save(vacuna);
        return VacunaMapper.toDTO(nuevaVacuna);
    }

    @Override
    public List<VacunaDTO> listarVacunasPorMascota(Long mascotaId) {
        return vacunaRepository.findByMascotaId(mascotaId)
                .stream()
                .map(VacunaMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<VacunaDTO> obtenerVacunaPorId(Long id) {
        return vacunaRepository.findById(id)
                .map(VacunaMapper::toDTO);
    }

    @Override
    public void eliminarVacuna(Long id) {
        vacunaRepository.deleteById(id);
    }
}
