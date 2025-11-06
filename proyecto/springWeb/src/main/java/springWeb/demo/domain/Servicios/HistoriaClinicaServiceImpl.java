package springWeb.demo.domain.Servicios;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.HistoriaClinicaDTO;
import springWeb.demo.domain.Dto.RecetaDTO;
import springWeb.demo.domain.Dto.TratamientoDTO;
import springWeb.demo.domain.Mapper.HistoriaClinicaMapper;
import springWeb.demo.domain.Mapper.RecetaMapper;
import springWeb.demo.domain.Mapper.TratamientoMapper;
import springWeb.demo.domain.Modelos.HistoriaClinica;
import springWeb.demo.domain.Modelos.Mascota;
import springWeb.demo.domain.Repositorios.HistoriaClinicaRepository;
import springWeb.demo.domain.Repositorios.MascotaRepository;
import springWeb.demo.domain.Repositorios.RecetaRepository;
import springWeb.demo.domain.Repositorios.TratamientoRepository;
import springWeb.demo.domain.Servicios.interfaces.HistoriaClinicaService;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final TratamientoRepository tratamientoRepository;
    private final RecetaRepository recetaRepository;

    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final MascotaRepository mascotaRepository;
    // No se necesita la instancia del mapper si todos los métodos son estáticos

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

    // ▼▼▼ MÉTODO NUEVO Y CORREGIDO QUE SOLUCIONA EL ERROR ▼▼▼
    @Override
    public HistoriaClinicaDTO guardarHistoria(HistoriaClinicaDTO historiaClinicaDTO) {
        // 1. Buscar la mascota a la que pertenece esta historia usando el ID del DTO
        Mascota mascota = mascotaRepository.findById(historiaClinicaDTO.getMascotaId())
                .orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con id: " + historiaClinicaDTO.getMascotaId()));

        // 2. Convertir el DTO a una entidad del modelo
        HistoriaClinica historiaClinica = HistoriaClinicaMapper.toEntity(historiaClinicaDTO);

        // 3. Establecer las relaciones y valores por defecto
        historiaClinica.setMascota(mascota);
        historiaClinica.setFecha(LocalDateTime.now()); // Asignar la fecha y hora actual

        // 4. Guardar la entidad en la base de datos
        HistoriaClinica historiaGuardada = historiaClinicaRepository.save(historiaClinica);

        // 5. Convertir la entidad guardada de nuevo a DTO para devolverla
        return HistoriaClinicaMapper.toDTO(historiaGuardada);
    }
    @Override
    public List<TratamientoDTO> listarTratamientosPorMascota(Long mascotaId) {
        return tratamientoRepository.findByMascotaId(mascotaId).stream()
                .map(TratamientoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecetaDTO> listarRecetasPorMascota(Long mascotaId) {
        return recetaRepository.findByMascotaId(mascotaId).stream()
                .map(RecetaMapper::toDTO)
                .collect(Collectors.toList());
    }
}