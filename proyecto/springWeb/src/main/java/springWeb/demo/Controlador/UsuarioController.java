package springWeb.demo.Controlador;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springWeb.demo.domain.Dto.UsuarioDTO;
import springWeb.demo.domain.Modelos.Usuario;
import springWeb.demo.domain.Servicios.interfaces.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO nuevoUsuario = usuarioService.registrarUsuario(usuarioDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> loginUsuario(@RequestBody Map<String, String> loginData) {
        try {
            String email = loginData.get("email");
            String contraseña = loginData.get("contraseña");

            // Usar el método que creamos que retorna la entidad Usuario
            Usuario usuario = usuarioService.verificarUsuario(email, contraseña);

            // Convertir a DTO para la respuesta (igual que en registro)
            UsuarioDTO usuarioDTO = usuarioService.buscarPorEmail(email)
                    .orElseThrow(() -> new RuntimeException("Error al convertir usuario"));

            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

}
