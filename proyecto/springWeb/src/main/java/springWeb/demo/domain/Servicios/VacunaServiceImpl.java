package springWeb.demo.domain.Servicios;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.VacunaDTO;
import springWeb.demo.domain.Mapper.VacunaMapper;
import springWeb.demo.domain.Modelos.Mascota;
import springWeb.demo.domain.Modelos.Vacuna;
import springWeb.demo.domain.Repositorios.MascotaRepository;
import springWeb.demo.domain.Repositorios.VacunaRepository;
import springWeb.demo.domain.Servicios.interfaces.VacunaService;

@Service
@RequiredArgsConstructor
public class VacunaServiceImpl implements VacunaService {

    private final VacunaRepository vacunaRepository;
    private final MascotaRepository mascotaRepository;

    @Override
    public VacunaDTO guardarVacuna(VacunaDTO vacunaDTO) {
        Mascota mascota = mascotaRepository.findById(vacunaDTO.getMascotaId())
                .orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con id: " + vacunaDTO.getMascotaId()));

        Vacuna vacuna = VacunaMapper.toEntity(vacunaDTO);
        vacuna.setMascota(mascota);

        Vacuna vacunaGuardada = vacunaRepository.save(vacuna);

        return VacunaMapper.toDTO(vacunaGuardada);
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