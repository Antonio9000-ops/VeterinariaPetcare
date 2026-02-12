package springWeb.demo.domain.Servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springWeb.demo.domain.Dto.facturacion.DeudaDetalleDTO;
import springWeb.demo.domain.Dto.facturacion.DeudaTotalDTO;
import springWeb.demo.domain.Modelos.*;
import springWeb.demo.domain.Repositorios.CitaRepository;
import springWeb.demo.domain.Repositorios.FacturaRepository;
import springWeb.demo.domain.Repositorios.ItemFacturableRepository;
import springWeb.demo.domain.Servicios.interfaces.FacturacionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturacionServiceImpl implements FacturacionService {

    private final FacturaRepository facturaRepository;
    private final CitaRepository citaRepository;
    private final ItemFacturableRepository itemFacturableRepository;

    @Override
    public DeudaTotalDTO getDeudaPendiente(Usuario usuario) {
        List<Factura> facturasPendientes = facturaRepository.findByCita_Mascota_UsuarioAndEstadoPago(usuario,
                EstadoPago.PENDIENTE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<DeudaDetalleDTO> detalles = facturasPendientes.stream()
                .map(factura -> new DeudaDetalleDTO(
                        "Factura para " + factura.getCita().getMascota().getNombre() +
                                " (" + factura.getFechaEmision().format(formatter) + ")",
                        factura.getTotal()))
                .collect(Collectors.toList());

        BigDecimal total = detalles.stream()
                .map(DeudaDetalleDTO::monto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new DeudaTotalDTO(total, detalles);
    }

    @Override
    @Transactional
    public Factura crearFacturaParaCita(Long citaId, List<Long> itemIds) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + citaId));

        if (facturaRepository.findByCitaId(citaId).isPresent()) {
            throw new IllegalStateException("La cita ya tiene una factura generada.");
        }

        List<ItemFacturable> items = itemFacturableRepository.findAllById(itemIds);
        if (items.size() != itemIds.size()) {
            throw new RuntimeException("Algunos de los items facturables no fueron encontrados.");
        }

        BigDecimal total = items.stream()
                .map(ItemFacturable::getPrecio) // Esto ahora funcionará correctamente
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Factura nuevaFactura = Factura.builder()
                .cita(cita)
                .items(items)
                .total(total)
                .fechaEmision(LocalDateTime.now())
                .estadoPago(EstadoPago.PENDIENTE)
                .build();

        facturaRepository.save(nuevaFactura);

        cita.setEstadoPago(EstadoPago.PENDIENTE);
        citaRepository.save(cita);

        return nuevaFactura;
    }

    @Override
    public Optional<Factura> obtenerFacturaPorCitaId(Long citaId) {
        return facturaRepository.findByCitaId(citaId);
    }

    @Override
    @Transactional
    public Factura marcarFacturaComoPagada(Long facturaId) {
        Factura factura = facturaRepository.findById(facturaId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + facturaId));

        factura.setEstadoPago(EstadoPago.PAGADO);
        factura.getCita().setEstadoPago(EstadoPago.PAGADO);

        facturaRepository.save(factura);
        citaRepository.save(factura.getCita());

        return factura;
    }
}