package springWeb.demo.domain.Dto.facturacion;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class FacturaRequestDTO {

    @NotNull(message = "El id de la cita es obligatorio")
    private Long citaId;

    @NotEmpty(message = "Debes enviar al menos un item facturable")
    private List<Long> itemIds;
}
