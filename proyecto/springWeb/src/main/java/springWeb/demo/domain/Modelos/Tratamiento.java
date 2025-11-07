package springWeb.demo.domain.Modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tratamientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private LocalDate fecha;
    @Column(precision = 10, scale = 2) 
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;
    @ManyToOne
    @JoinColumn(name = "historia_clinica_id")
    @JsonBackReference("historia-tratamiento")
    private HistoriaClinica historiaClinica;
}