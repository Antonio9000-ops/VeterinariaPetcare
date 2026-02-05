package springWeb.demo.domain.Dto.facturacion;

import java.math.BigDecimal;

public record DeudaDetalleDTO(
    String concepto,
    BigDecimal monto
) {}