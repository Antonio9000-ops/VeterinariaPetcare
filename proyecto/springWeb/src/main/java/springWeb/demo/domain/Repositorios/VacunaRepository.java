package springWeb.demo.domain.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springWeb.demo.domain.Modelos.Vacuna;


@Repository
public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
    List<Vacuna> findByMascotaId(Long mascotaId);
}
