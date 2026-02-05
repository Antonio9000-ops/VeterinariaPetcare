package springWeb.demo.domain.Mapper;

import org.springframework.stereotype.Component;
import springWeb.demo.domain.Dto.PagoDTO;
import springWeb.demo.domain.Modelos.Pago;

@Component
public class PagoMapper {

    public PagoDTO toDto(Pago pago) {
        if (pago == null) {
            return null;
        }

        return PagoDTO.builder()
                .id(pago.getId())
                .numeroTransaccion(pago.getNumeroTransaccion())
                .monto(pago.getMonto())
                .moneda(pago.getMoneda())
                .estado(pago.getEstado())
                .fechaCreacion(pago.getFechaCreacion())
                .build();
    }
}