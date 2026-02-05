package springWeb.demo.domain.Dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springWeb.demo.domain.Modelos.Rol;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;
    private String contraseña;
    private String nombre;
    private String email;
    private Rol rol;

    // Si quieres mostrar también las mascotas, puedes devolver solo sus nombres o
    // IDs
    private List<String> mascotas;

    // Igual con las citas, puedes devolver solo sus IDs
    private List<Long> citasAsignadas;
}
