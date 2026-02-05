package springWeb.demo.domain.Servicios.interfaces;

import springWeb.demo.domain.Dto.facturacion.DeudaTotalDTO;
import springWeb.demo.domain.Modelos.Factura;
import springWeb.demo.domain.Modelos.Usuario;

import java.util.List;
import java.util.Optional;

public interface FacturacionService {

    DeudaTotalDTO getDeudaPendiente(Usuario usuario);

    Factura crearFacturaParaCita(Long citaId, List<Long> itemIds);

    Optional<Factura> obtenerFacturaPorCitaId(Long citaId);

    Factura marcarFacturaComoPagada(Long facturaId);
}