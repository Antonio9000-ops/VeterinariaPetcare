package springWeb.demo.domain.Modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicamento;
    private String dosis;
    private String duracion;

    @ManyToOne
    @JoinColumn(name = "historia_clinica_id")
    @JsonBackReference("historia-receta")
    private HistoriaClinica historiaClinica;
}