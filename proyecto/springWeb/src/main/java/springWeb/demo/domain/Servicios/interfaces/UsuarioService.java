
package springWeb.demo.domain.Servicios.interfaces;

import java.util.List;
import java.util.Optional;
import springWeb.demo.domain.Dto.UsuarioDTO;
import springWeb.demo.domain.Modelos.Usuario;

public interface UsuarioService {

    UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);

    Optional<UsuarioDTO> buscarPorEmail(String email);

    List<UsuarioDTO> listarUsuarios();

    Optional<UsuarioDTO> buscarPorId(Long id);

    void eliminarUsuario(Long id);

    Usuario verificarUsuario(String email, String contraseña);
}
