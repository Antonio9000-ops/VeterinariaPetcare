package springWeb.demo.domain.Dto.facturacion;

import java.math.BigDecimal;
import java.util.List;

public record DeudaTotalDTO(
    BigDecimal total,
    List<DeudaDetalleDTO> detalles
) {
    
}