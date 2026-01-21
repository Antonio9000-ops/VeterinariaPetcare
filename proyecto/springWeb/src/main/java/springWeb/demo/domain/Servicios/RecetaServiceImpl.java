package springWeb.demo.domain.Servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.RecetaDTO;
import springWeb.demo.domain.Mapper.RecetaMapper;
import springWeb.demo.domain.Modelos.HistoriaClinica;
import springWeb.demo.domain.Modelos.Receta;
import springWeb.demo.domain.Repositorios.HistoriaClinicaRepository;
import springWeb.demo.domain.Repositorios.RecetaRepository;
import springWeb.demo.domain.Servicios.interfaces.RecetaService;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository recetaRepository;
    private final HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public RecetaDTO guardarReceta(RecetaDTO recetaDTO) {
        
        Receta receta = RecetaMapper.toEntity(recetaDTO);

        Long historiaId = recetaDTO.getHistoriaClinicaId();
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(historiaId)
                .orElseThrow(() -> new EntityNotFoundException("Historia Clínica no encontrada con el ID: " + historiaId));

        receta.setHistoriaClinica(historiaClinica);

        Receta recetaGuardada = recetaRepository.save(receta);

        
        return RecetaMapper.toDTO(recetaGuardada);
    }
}