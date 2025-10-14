package springWeb.demo.Security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import springWeb.demo.Security.jwt.JwtAuthFilter;

import springWeb.demo.Security.jwt.JwtAuthFilter;
import springWeb.demo.Security.jwt.JwtService;
import springWeb.demo.domain.Repositorios.UsuarioRepository;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // --- Rutas Públicas ---
                        // Permite el acceso a la página de inicio, la autenticación y los recursos estáticos.
                        .requestMatchers("/", "/inicio.html", "/auth/**").permitAll()
                        .requestMatchers("/mascotas", "/mascota-detalle", "/mascota-formulario", "/cita-formulario").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/*.ico").permitAll()

                        // --- Rutas Protegidas de la API ---
                        // Se mantienen las reglas específicas para la API.
                        .requestMatchers("/api/usuarios/**").permitAll() // Asumo que esto es para registro o consulta pública
                        .requestMatchers("/api/mascotas/**").hasAnyRole("DUEÑO", "ASISTENTE", "VETERINARIO")
                        .requestMatchers("/api/citas/**").hasAnyRole("DUEÑO", "ASISTENTE", "VETERINARIO")
                        .requestMatchers("/api/historias/**").hasAnyRole("VETERINARIO")
                        .requestMatchers("/api/vacunas/**").hasAnyRole("VETERINARIO")

                        // --- Regla Final ---
                        // Cualquier otra solicitud que no coincida con las reglas anteriores debe estar autenticada.
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtAuthFilter(jwtService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",
                "http://127.0.0.1:5500",
                "http://localhost:8080",
                "http://127.0.0.1:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Cache-Control", "X-Requested-With", "Accept", "Origin"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}