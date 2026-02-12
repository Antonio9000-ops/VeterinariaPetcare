package springWeb.demo.domain.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import springWeb.demo.domain.Modelos.EstadoPago;
import springWeb.demo.domain.Modelos.Tratamiento;
import springWeb.demo.domain.Modelos.Usuario;

import java.util.List;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    @Query("SELECT t FROM Tratamiento t WHERE t.historiaClinica.mascota.id = :mascotaId")
    List<Tratamiento> findByMascotaId(@Param("mascotaId") Long mascotaId);

    // Cambio aquí: .mascota.usuario en lugar de .mascota.dueno
    @Query("SELECT t FROM Tratamiento t WHERE t.historiaClinica.mascota.usuario = :dueno AND t.estadoPago = :estado")
    List<Tratamiento> findByUsuarioAndEstadoPago(@Param("usuario") Usuario usuario, @Param("estado") EstadoPago estado);
}