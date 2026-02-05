package springWeb.demo.domain.Modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cita_id", referencedColumnName = "id")
    private Cita cita;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "factura_items",
        joinColumns = @JoinColumn(name = "factura_id"),
        inverseJoinColumns = @JoinColumn(name = "item_facturable_id")
    )
    private List<ItemFacturable> items;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    private LocalDateTime fechaEmision;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;
}