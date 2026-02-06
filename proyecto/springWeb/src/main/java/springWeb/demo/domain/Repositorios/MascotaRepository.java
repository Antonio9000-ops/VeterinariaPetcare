package springWeb.demo.domain.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springWeb.demo.domain.Modelos.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // Cambio puntual: de DuenoId a UsuarioId
    List<Mascota> findByUsuarioId(Long usuarioId);

    @Query("SELECT DISTINCT m FROM Mascota m JOIN m.citas c WHERE c.estado = 'ACEPTADA'")
    List<Mascota> findMascotasConCitaAceptada();
}
