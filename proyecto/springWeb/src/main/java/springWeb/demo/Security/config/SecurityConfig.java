package springWeb.demo.Security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import springWeb.demo.Security.jwt.JwtAuthFilter;
import springWeb.demo.Security.jwt.JwtService;
import springWeb.demo.domain.Repositorios.UsuarioRepository;

import java.util.Arrays;
import org.springframework.web.cors.CorsConfigurationSource;

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
                       .requestMatchers("/gestion-servicios").hasAnyAuthority("VETERINARIO", "ASISTENTE")
                        .requestMatchers("/api/items-facturables/**").hasAnyAuthority("VETERINARIO", "ASISTENTE")
                        .requestMatchers(
                                "/", "/inicio.html", "/login", "/login.html",
                                "/register", "/register.html", "/auth/**",
                                "/mascotas", "/mascota-detalle", "/mascota-formulario",
                                "/cita-formulario", "/agenda", "/agenda.html",
                                "/historia-formulario", "/historia-formulario.html",
                                "/vacuna-formulario", "/vacuna-formulario.html",
                                "/gestion-citas", "/gestion-citas.html",
                                "/tratamiento-formulario", "/receta-formulario",
                                "/pagos", "/pagos.html",
                                "/css/**", "/js/**", "/images/**", "/favicon.ico", "/img/**", "/lib/**" ,"/scss/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/facturacion/crear").hasAnyAuthority("VETERINARIO", "ASISTENTE")
                        .requestMatchers(HttpMethod.GET, "/api/facturacion/cita/**").hasAnyAuthority("DUEÑO", "VETERINARIO", "ASISTENTE")
                        .requestMatchers(HttpMethod.PUT, "/api/facturacion/**/pagar").hasAnyAuthority("VETERINARIO", "ASISTENTE")
                        .requestMatchers("/api/pagos/**").hasAuthority("DUEÑO")
                        
                        .requestMatchers(HttpMethod.POST, "/api/tratamientos", "/api/recetas").hasAuthority("VETERINARIO")
                        .requestMatchers(HttpMethod.POST, "/api/historias", "/api/vacunas").hasAuthority("VETERINARIO")
                        
                        .requestMatchers(HttpMethod.GET, "/api/citas/pendientes").hasAnyAuthority("VETERINARIO", "ASISTENTE")
                        .requestMatchers(HttpMethod.PUT, "/api/citas/**").hasAnyAuthority("VETERINARIO", "ASISTENTE")

                        .requestMatchers("/api/mascotas/dueno/**").hasAuthority("DUEÑO")
                        .requestMatchers("/api/mascotas/**").hasAnyAuthority("DUEÑO", "ASISTENTE", "VETERINARIO")
                        
                        .requestMatchers("/api/citas/veterinario/**").hasAnyAuthority("VETERINARIO", "ASISTENTE")
                        .requestMatchers("/api/citas/**").hasAnyAuthority("DUEÑO", "VETERINARIO", "ASISTENTE")

                        .requestMatchers("/api/historias/**").hasAnyAuthority("DUEÑO", "VETERINARIO")
                        .requestMatchers("/api/vacunas/**").hasAnyAuthority("DUEÑO", "VETERINARIO")
                        
                        .requestMatchers("/api/usuarios/**").permitAll()

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
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://127.0.0.1:5500", "http://localhost:8092"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}