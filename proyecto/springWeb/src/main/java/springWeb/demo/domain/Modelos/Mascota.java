package springWeb.demo.domain.Modelos;

import jakarta.persistence.Entity;

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
    @JoinColumn(name = "dueno_id")
    private Usuario dueno;

    @OneToMany(mappedBy = "mascota")
    private List<Cita> citas;

    @OneToMany(mappedBy = "mascota")
    private List<HistoriaClinica> historiaClinica;

    @OneToMany(mappedBy = "mascota")
    private List<Vacuna> vacunas;
}
