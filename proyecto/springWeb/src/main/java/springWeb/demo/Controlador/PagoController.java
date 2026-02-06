package springWeb.demo.Controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springWeb.demo.domain.Dto.facturacion.DeudaTotalDTO;
import springWeb.demo.domain.Modelos.EstadoPago;
import springWeb.demo.domain.Modelos.Factura;
import springWeb.demo.domain.Modelos.Pago;
import springWeb.demo.domain.Modelos.Usuario;
import springWeb.demo.domain.Repositorios.FacturaRepository;
import springWeb.demo.domain.Repositorios.PagoRepository;
import springWeb.demo.domain.Servicios.interfaces.FacturacionService;
import springWeb.demo.domain.Dto.PagoDTO;
import springWeb.demo.domain.Mapper.PagoMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoRepository pagoRepository;
    private final FacturacionService facturacionService;
    private final FacturaRepository facturaRepository;
    private final PagoMapper pagoMapper;

    @PostMapping("/simular-pago")
    @Transactional
    public ResponseEntity<Map<String, String>> simulatePayment(@AuthenticationPrincipal Usuario usuario) {

        DeudaTotalDTO deuda = facturacionService.getDeudaPendiente(usuario);
        BigDecimal monto = deuda.total();

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "No tienes pagos pendientes."));
        }

        Pago pago = Pago.builder()
                .numeroTransaccion(UUID.randomUUID().toString())
                .monto(monto)
                .moneda("USD")
                .estado("APROBADO")
                .fechaCreacion(LocalDateTime.now())
                .usuario(usuario)
                .build();

        pagoRepository.save(pago);

        // --- LÓGICA ACTUALIZADA ---
        // 1. Buscar todas las facturas pendientes del usuario
        List<Factura> facturasPendientes = facturaRepository.findByCita_Mascota_UsuarioAndEstadoPago(usuario,
                EstadoPago.PENDIENTE);

        // 2. Marcar cada una como pagada usando el servicio
        for (Factura factura : facturasPendientes) {
            facturacionService.marcarFacturaComoPagada(factura.getId());
        }

        return ResponseEntity.ok(Map.of("mensaje", "Pago realizado exitosamente"));
    }

    @GetMapping("/mis-pagos")
    public ResponseEntity<List<PagoDTO>> getMyPayments(@AuthenticationPrincipal Usuario usuario) {
        List<Pago> pagos = pagoRepository.findByUsuarioId(usuario.getId());

        List<PagoDTO> pagosDto = pagos.stream()
                .map(pagoMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pagosDto);
    }
}