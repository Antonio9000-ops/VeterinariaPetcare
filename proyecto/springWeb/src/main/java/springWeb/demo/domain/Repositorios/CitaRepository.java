package springWeb.demo.domain.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springWeb.demo.domain.Modelos.Cita;
import springWeb.demo.domain.Modelos.EstadoCita;
import springWeb.demo.domain.Modelos.EstadoPago;
import springWeb.demo.domain.Modelos.Usuario;


@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByVeterinarioId(Long veterinarioId);
    List<Cita> findByMascotaId(Long mascotaId);
    List<Cita> findByEstado(EstadoCita estado);
    List<Cita> findByMascota_DuenoAndEstadoPago(Usuario dueno, EstadoPago estado);
}
