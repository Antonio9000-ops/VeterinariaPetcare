package springWeb.demo.domain.Servicios;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.MascotaDTO;
import springWeb.demo.domain.Mapper.MascotaMapper;
import springWeb.demo.domain.Modelos.Mascota;
import springWeb.demo.domain.Repositorios.MascotaRepository;
import springWeb.demo.domain.Servicios.interfaces.MascotaService;

@Service
@RequiredArgsConstructor

public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final MascotaMapper mascotaMapper;

    @Override
    public MascotaDTO registrarMascota(MascotaDTO mascotaDTO) {
        Mascota mascota = MascotaMapper.toEntity(mascotaDTO); // <- estático
        Mascota nuevaMascota = mascotaRepository.save(mascota);
        return MascotaMapper.toDTO(nuevaMascota); // <- estático
    }

    @Override
    public List<MascotaDTO> listarMascotasPorDueño(Long duenoId) {
        return mascotaRepository.findByDuenoId(duenoId)
                .stream()
                .map(MascotaMapper::toDTO) // <- estático
                .toList();
    }

    @Override
    public Optional<MascotaDTO> obtenerMascotaPorId(Long id) {
        return mascotaRepository.findById(id)
                .map(MascotaMapper::toDTO); // <- estático
    }

    @Override
    public MascotaDTO actualizarMascota(MascotaDTO mascotaDTO) {
        Mascota mascota = MascotaMapper.toEntity(mascotaDTO); // <- estático
        Mascota actualizada = mascotaRepository.save(mascota);
        return MascotaMapper.toDTO(actualizada); // <- estático
    }

    @Override
    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

}
