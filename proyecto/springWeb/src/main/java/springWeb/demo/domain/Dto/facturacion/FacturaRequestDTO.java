package springWeb.demo.domain.Dto.facturacion;

import lombok.Data;
import java.util.List;

@Data
public class FacturaRequestDTO {
    private Long citaId;
    private List<Long> itemIds;
}