package springWeb.demo.Security.auth;

// RegisterRequest.java

import lombok.Data;
import springWeb.demo.domain.Modelos.Rol;

@Data
public class RegisterRequest {
    private String nombre;
    private String email;
    private String contraseña;
    private Rol rol;
}
