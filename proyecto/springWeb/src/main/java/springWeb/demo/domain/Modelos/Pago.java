package springWeb.demo.domain.Modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String numeroTransaccion;

    private BigDecimal monto;
    private String moneda;
    private String estado; 
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    
    @ManyToOne
    @JoinColumn(name = "cita_id")
    private Cita cita;
}