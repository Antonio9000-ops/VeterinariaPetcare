package springWeb.demo.Security.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Modelos.Usuario;
import springWeb.demo.domain.Repositorios.UsuarioRepository;
import springWeb.demo.Security.jwt.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Usuario registrar(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .contraseña(passwordEncoder.encode(request.getContraseña()))
                .rol(request.getRol())
                .build();

        return usuarioRepository.save(usuario);
    }

    public AuthResponse login(AuthRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getContraseña(), usuario.getContraseña())) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }

        String token = jwtService.generateToken(usuario);
        return new AuthResponse(token);
    }
}
