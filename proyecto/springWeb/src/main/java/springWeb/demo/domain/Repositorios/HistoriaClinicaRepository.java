package springWeb.demo.domain.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springWeb.demo.domain.Modelos.HistoriaClinica;


@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {
    List<HistoriaClinica> findByMascotaId(Long mascotaId);
}

