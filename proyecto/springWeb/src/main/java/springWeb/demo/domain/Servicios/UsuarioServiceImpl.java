package springWeb.demo.domain.Servicios;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.UsuarioDTO;
import springWeb.demo.domain.Mapper.UsuarioMapper;
import springWeb.demo.domain.Modelos.Usuario;
import springWeb.demo.domain.Repositorios.UsuarioRepository;
import springWeb.demo.domain.Servicios.interfaces.UsuarioService;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        // Convertir DTO a entidad
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);


        Usuario nuevoUsuario = usuarioRepository.save(usuario);


        return usuarioMapper.toDTO(nuevoUsuario);
    }

    @Override
    public Optional<UsuarioDTO> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioMapper::toDTO);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario verificarUsuario(String email, String contraseña) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        if (!usuario.getContraseña().equals(contraseña)) {
            throw new RuntimeException("Contraseña incorrecta");
        }


        return usuario;
    }

}
