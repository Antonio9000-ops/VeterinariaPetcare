package springWeb.demo.domain.Servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<MascotaDTO> listarTodasLasMascotas() {
        return mascotaRepository.findAll()
                .stream()

                .map(mascotaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaDTO registrarMascota(MascotaDTO mascotaDTO) {

        Mascota mascota = mascotaMapper.toEntity(mascotaDTO);
        Mascota nuevaMascota = mascotaRepository.save(mascota);
        return mascotaMapper.toDTO(nuevaMascota);
    }

    @Override
    public List<MascotaDTO> listarMascotasPorDueño(Long duenoId) {
        return mascotaRepository.findByDuenoId(duenoId)
                .stream()

                .map(mascotaMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<MascotaDTO> obtenerMascotaPorId(Long id) {
        return mascotaRepository.findById(id)

                .map(mascotaMapper::toDTO);
    }

    @Override
    public MascotaDTO actualizarMascota(MascotaDTO mascotaDTO) {

        Mascota mascota = mascotaMapper.toEntity(mascotaDTO);
        Mascota actualizada = mascotaRepository.save(mascota);
        return mascotaMapper.toDTO(actualizada);
    }

    @Override
    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

    public List<Mascota> findMascotasConCitaAceptada() {
        return mascotaRepository.findMascotasConCitaAceptada();
    }

}
