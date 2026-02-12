package springWeb.demo.domain.Servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.MascotaDTO;
import springWeb.demo.domain.Mapper.MascotaMapper;
import springWeb.demo.domain.Modelos.Mascota;
import springWeb.demo.domain.Modelos.Usuario;
import springWeb.demo.domain.Repositorios.MascotaRepository;
import springWeb.demo.domain.Repositorios.UsuarioRepository; // Importación clave
import springWeb.demo.domain.Servicios.interfaces.MascotaService;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final MascotaMapper mascotaMapper;
    private final UsuarioRepository usuarioRepository; // Inyectado automáticamente por @RequiredArgsConstructor

    @Override
    public List<MascotaDTO> listarTodasLasMascotas() {
        return mascotaRepository.findAll()
                .stream()
                .map(mascotaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaDTO registrarMascota(MascotaDTO mascotaDTO) {
        // 1. Convertimos el DTO a la entidad Mascota
        Mascota mascota = mascotaMapper.toEntity(mascotaDTO);

        // 2. ASIGNACIÓN AUTOMÁTICA DEL DUEÑO
        // Verificamos si el DTO trae un usuarioId (enviado desde el frontend)
        if (mascotaDTO.getUsuarioId() != null) {
            // Buscamos al usuario real en la base de datos
            Usuario usuario = usuarioRepository.findById(mascotaDTO.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException(
                            "Error: Usuario con ID " + mascotaDTO.getUsuarioId() + " no encontrado"));

            // Establecemos la relación. Esto llena la columna 'usuario_id' en la tabla
            // mascotas
            mascota.setUsuario(usuario);
        }

        // 3. Guardamos la mascota ya vinculada
        Mascota guardada = mascotaRepository.save(mascota);
        return mascotaMapper.toDTO(guardada);
    }

    @Override
    public List<MascotaDTO> listarMascotasPorUsuario(Long usuarioId) {
        // Log para depuración en consola
        System.out.println("Buscando mascotas vinculadas al usuario ID: " + usuarioId);

        List<Mascota> mascotas = mascotaRepository.findByUsuarioId(usuarioId);

        System.out.println("Resultado: " + mascotas.size() + " mascotas encontradas.");

        return mascotas.stream()
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
        // Al actualizar, buscamos la mascota existente
        Mascota mascotaExistente = mascotaRepository.findById(mascotaDTO.getId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada para actualizar"));

        // Actualizamos los campos básicos
        mascotaExistente.setNombre(mascotaDTO.getNombre());
        mascotaExistente.setEspecie(mascotaDTO.getEspecie());
        mascotaExistente.setRaza(mascotaDTO.getRaza());
        mascotaExistente.setFechaNacimiento(mascotaDTO.getFechaNacimiento());

        // Si se envía un usuarioId, actualizamos la relación también
        if (mascotaDTO.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(mascotaDTO.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            mascotaExistente.setUsuario(usuario);
        }

        Mascota actualizada = mascotaRepository.save(mascotaExistente);
        return mascotaMapper.toDTO(actualizada);
    }

    @Override
    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    public List<Mascota> findMascotasConCitaAceptada() {
        return mascotaRepository.findMascotasConCitaAceptada();
    }
}