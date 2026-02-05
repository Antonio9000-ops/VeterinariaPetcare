package springWeb.demo.domain.Mapper;

import org.springframework.stereotype.Component;
import springWeb.demo.domain.Dto.RecetaDTO;
import springWeb.demo.domain.Modelos.Receta;

@Component
public class RecetaMapper {

    public static RecetaDTO toDTO(Receta receta) {
        if (receta == null) return null;

        return RecetaDTO.builder()
                .id(receta.getId())
                .medicamento(receta.getMedicamento())
                .dosis(receta.getDosis())
                .duracion(receta.getDuracion())
                .build();
    }

    public static Receta toEntity(RecetaDTO dto) {
        if (dto == null) return null;

        return Receta.builder()
                .id(dto.getId())
                .medicamento(dto.getMedicamento())
                .dosis(dto.getDosis())
                .duracion(dto.getDuracion())
                .build();
    }
}