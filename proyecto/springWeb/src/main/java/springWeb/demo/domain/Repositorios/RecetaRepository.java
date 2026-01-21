package springWeb.demo.domain.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springWeb.demo.domain.Modelos.Receta;
import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    @Query("SELECT r FROM Receta r WHERE r.historiaClinica.mascota.id = :mascotaId")
    List<Receta> findByMascotaId(@Param("mascotaId") Long mascotaId);
}