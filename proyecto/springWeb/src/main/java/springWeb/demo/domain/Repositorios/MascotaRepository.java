package springWeb.demo.domain.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springWeb.demo.domain.Modelos.Mascota;
import springWeb.demo.domain.Modelos.EstadoCita;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    List<Mascota> findByDuenoId(Long duenoId);


    List<Mascota> findDistinctByCitasEstado(EstadoCita estado);
}
