// Asegúrate de que tu clase ItemFacturable se vea así:
package springWeb.demo.domain.Modelos;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "items_facturables")
@Data
public class ItemFacturable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @Column(precision = 10, scale = 2) 
    private BigDecimal precio; 

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;
}