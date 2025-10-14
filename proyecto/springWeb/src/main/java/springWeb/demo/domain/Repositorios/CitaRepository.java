package springWeb.demo.domain.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springWeb.demo.domain.Modelos.Cita;


@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByVeterinarioId(Long veterinarioId);
    List<Cita> findByMascotaId(Long mascotaId);
}
