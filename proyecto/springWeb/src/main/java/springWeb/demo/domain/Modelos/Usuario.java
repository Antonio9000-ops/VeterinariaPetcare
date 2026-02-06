package springWeb.demo.domain.Modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String contraseña;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference("usuario-mascota")
    @ToString.Exclude
    private List<Mascota> mascotas;

    @OneToMany(mappedBy = "veterinario")
    @JsonManagedReference("usuario-cita")
    @ToString.Exclude
    private List<Cita> citasAsignadas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devolvemos una lista con una única autoridad basada en el rol del usuario.
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getPassword() {
        // Spring Security usará este método para obtener la contraseña hasheada.
        return contraseña;
    }

    @Override
    public String getUsername() {
        // En nuestra aplicación, el "username" es el email.
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}