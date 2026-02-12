package springWeb.demo.Controlador;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springWeb.demo.domain.Dto.facturacion.DeudaTotalDTO;
import springWeb.demo.domain.Dto.facturacion.FacturaRequestDTO;
import springWeb.demo.domain.Modelos.Factura;
import springWeb.demo.domain.Modelos.Usuario;
import springWeb.demo.domain.Servicios.interfaces.FacturacionService;

@RestController
@RequestMapping("/api/facturacion")
@RequiredArgsConstructor
public class FacturacionController {

    private final FacturacionService facturacionService;

    @GetMapping("/deuda-pendiente")
    public ResponseEntity<DeudaTotalDTO> getDeudaPendiente(@AuthenticationPrincipal Usuario usuario) {
        DeudaTotalDTO deuda = facturacionService.getDeudaPendiente(usuario);
        return ResponseEntity.ok(deuda);
    }

    @PostMapping("/crear")
    public ResponseEntity<Factura> crearFactura(@Valid @RequestBody FacturaRequestDTO request) {
        Factura nuevaFactura = facturacionService.crearFacturaParaCita(request.getCitaId(), request.getItemIds());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFactura);
    }

    @GetMapping("/cita/{citaId}")
    public ResponseEntity<?> getFacturaPorCitaId(@PathVariable Long citaId) {
        return facturacionService.obtenerFacturaPorCitaId(citaId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{facturaId}/pagar")
    public ResponseEntity<Factura> marcarComoPagada(@PathVariable Long facturaId) {
        Factura facturaPagada = facturacionService.marcarFacturaComoPagada(facturaId);
        return ResponseEntity.ok(facturaPagada);
    }
}
