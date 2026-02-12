package springWeb.demo.domain.Modelos;

import jakarta.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "mascotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private String raza;
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference("usuario-mascota")
    private Usuario usuario;

    @OneToMany(mappedBy = "mascota")
    @JsonManagedReference("mascota-cita")
    @ToString.Exclude
    private List<Cita> citas;

    @OneToMany(mappedBy = "mascota")
    @JsonManagedReference("mascota-historia")
    @ToString.Exclude
    private List<HistoriaClinica> historiaClinica;

    @OneToMany(mappedBy = "mascota")
    @ToString.Exclude
    @JsonManagedReference("mascota-vacuna")
    private List<Vacuna> vacunas;
}
