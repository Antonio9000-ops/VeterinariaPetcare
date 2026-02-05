package springWeb.demo.Security.auth;

// AuthRequest.java

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String contraseña;
}
