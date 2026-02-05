package springWeb.demo.domain.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import springWeb.demo.domain.Modelos.Pago;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    
    List<Pago> findByUsuarioId(Long usuarioId);
}