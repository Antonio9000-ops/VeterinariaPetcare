package springWeb.demo.domain.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springWeb.demo.domain.Modelos.Tratamiento;
import java.util.List;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    
    @Query("SELECT t FROM Tratamiento t WHERE t.historiaClinica.mascota.id = :mascotaId")
    List<Tratamiento> findByMascotaId(@Param("mascotaId") Long mascotaId);
}