package springWeb.demo.Security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import springWeb.demo.domain.Modelos.Usuario;
import springWeb.demo.domain.Repositorios.UsuarioRepository;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        System.out.println("JwtAuthFilter ejecutándose para: " + request.getRequestURL());

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String email;

        // Verificar header de autorización
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("ℹNo se encontró header Authorization válido, continuando...");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            jwt = authHeader.substring(7);
            email = jwtService.extractEmail(jwt);
            System.out.println("Email extraído del token: " + email);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Usuario usuario = usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + email));

                if (jwtService.isTokenValid(jwt, usuario)) {
                    // Crear authorities basadas en el rol del usuario
                    String rolFromDB = usuario.getRol().name();
                    String authority = rolFromDB.startsWith("ROLE_") ? rolFromDB : "ROLE_" + rolFromDB;

                    // Debug información
                    System.out.println("=== DEBUG JWT FILTER ===");
                    System.out.println("Usuario autenticado: " + usuario.getEmail());
                    System.out.println("Rol en BD: " + rolFromDB);
                    System.out.println("Authority final: " + authority);
                    System.out.println("URL solicitada: " + request.getRequestURL());
                    System.out.println("=== FIN DEBUG ===");

                    // Crear token de autenticación
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            List.of(new SimpleGrantedAuthority(authority)));

                    // Establecer detalles de la autenticación
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Establecer en el contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    System.out.println("Autenticación establecida correctamente para: " + email);
                } else {
                    System.out.println("Token inválido para: " + email);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                    return;
                }
            } else {
                System.out.println("ℹEmail nulo o ya existe autenticación en el contexto");
            }
        } catch (Exception e) {
            System.err.println("Error en JwtAuthFilter: " + e.getMessage());
            // Limpiar contexto en caso de error
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error de autenticación: " + e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }
}