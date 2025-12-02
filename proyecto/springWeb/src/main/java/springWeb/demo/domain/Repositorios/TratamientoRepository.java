package springWeb.demo.domain.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import springWeb.demo.domain.Modelos.EstadoPago;
import springWeb.demo.domain.Modelos.Tratamiento;
import springWeb.demo.domain.Modelos.Usuario;
import java.util.List;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    List<Tratamiento> findByHistoriaClinicaMascotaId(Long mascotaId);

    List<Tratamiento> findByHistoriaClinicaMascotaDuenoAndEstadoPago(Usuario dueno, EstadoPago estado);
}
