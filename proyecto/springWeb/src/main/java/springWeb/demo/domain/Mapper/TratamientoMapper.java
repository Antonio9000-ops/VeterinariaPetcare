package springWeb.demo.domain.Mapper;

import org.springframework.stereotype.Component;
import springWeb.demo.domain.Dto.TratamientoDTO;
import springWeb.demo.domain.Modelos.Tratamiento;

@Component
public class TratamientoMapper {

   
    public static TratamientoDTO toDTO(Tratamiento tratamiento) {
        if (tratamiento == null) {
            return null;
        }

       
        return TratamientoDTO.builder()
                .id(tratamiento.getId())
                .descripcion(tratamiento.getDescripcion())
                .fecha(tratamiento.getFecha())
               
                .historiaClinicaId(tratamiento.getHistoriaClinica() != null ? tratamiento.getHistoriaClinica().getId() : null)
                .build();
    }

   
    public static Tratamiento toEntity(TratamientoDTO dto) {
        if (dto == null) {
            return null;
        }

       
        return Tratamiento.builder()
                .id(dto.getId())
                .descripcion(dto.getDescripcion())
                .fecha(dto.getFecha())
               
                .build();
    }
}