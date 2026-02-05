package springWeb.demo.domain.Dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PagoDTO {
    private Long id;
    private String numeroTransaccion;
    private BigDecimal monto;
    private String moneda;
    private String estado;
    private LocalDateTime fechaCreacion;
}