package springWeb.demo.domain.Servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.TratamientoDTO;
import springWeb.demo.domain.Mapper.TratamientoMapper;
import springWeb.demo.domain.Modelos.HistoriaClinica;
import springWeb.demo.domain.Modelos.Tratamiento;
import springWeb.demo.domain.Repositorios.HistoriaClinicaRepository;
import springWeb.demo.domain.Repositorios.TratamientoRepository;
import springWeb.demo.domain.Servicios.interfaces.TratamientoService;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class TratamientoServiceImpl implements TratamientoService {

    private final TratamientoRepository tratamientoRepository;
    private final HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public TratamientoDTO guardarTratamiento(TratamientoDTO tratamientoDTO) {
      
        Tratamiento tratamiento = TratamientoMapper.toEntity(tratamientoDTO);

        Long historiaId = tratamientoDTO.getHistoriaClinicaId();
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(historiaId)
                .orElseThrow(() -> new EntityNotFoundException("Historia Clínica no encontrada con el ID: " + historiaId));

        tratamiento.setHistoriaClinica(historiaClinica);

        Tratamiento tratamientoGuardado = tratamientoRepository.save(tratamiento);

       
        return TratamientoMapper.toDTO(tratamientoGuardado);
    }
}
