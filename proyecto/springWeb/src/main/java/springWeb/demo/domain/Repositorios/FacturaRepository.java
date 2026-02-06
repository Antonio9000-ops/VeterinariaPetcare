package springWeb.demo.domain.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import springWeb.demo.domain.Modelos.EstadoPago;
import springWeb.demo.domain.Modelos.Factura;
import springWeb.demo.domain.Modelos.Usuario;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

    Optional<Factura> findByCitaId(Long citaId);

    // Cambio puntual: de _Dueno a _Usuario
    List<Factura> findByCita_Mascota_UsuarioAndEstadoPago(Usuario usuario, EstadoPago estadoPago);
}