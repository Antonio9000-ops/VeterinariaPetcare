
package springWeb.demo.domain.Modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String contraseña;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "dueno", cascade = CascadeType.ALL)
    @JsonManagedReference("usuario-mascota")
    @ToString.Exclude
    private List<Mascota> mascotas;

    @OneToMany(mappedBy = "veterinario")
    @JsonManagedReference("usuario-cita")
    @ToString.Exclude
    private List<Cita> citasAsignadas;
}
