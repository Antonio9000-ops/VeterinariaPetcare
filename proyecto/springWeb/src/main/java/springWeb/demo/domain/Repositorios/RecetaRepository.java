package springWeb.demo.domain.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import springWeb.demo.domain.Modelos.Receta;
import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByHistoriaClinicaMascotaId(Long mascotaId);
}
