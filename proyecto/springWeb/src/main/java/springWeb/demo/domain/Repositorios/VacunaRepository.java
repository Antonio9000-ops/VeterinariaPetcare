package springWeb.demo.domain.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springWeb.demo.domain.Modelos.EstadoPago;
import springWeb.demo.domain.Modelos.Usuario;
import springWeb.demo.domain.Modelos.Vacuna;

@Repository
public interface VacunaRepository extends JpaRepository<Vacuna, Long> {

    List<Vacuna> findByMascotaId(Long mascotaId);

    // Cambio: de _Dueno a _Usuario para que coincida con el modelo Mascota
    List<Vacuna> findByMascota_UsuarioAndEstadoPago(Usuario usuario, EstadoPago estado);
}
