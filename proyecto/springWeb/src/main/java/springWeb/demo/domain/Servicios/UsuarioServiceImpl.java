package springWeb.demo.domain.Servicios;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Dto.UsuarioDTO;
import springWeb.demo.domain.Mapper.UsuarioMapper;
import springWeb.demo.domain.Modelos.Usuario;
import springWeb.demo.domain.Repositorios.UsuarioRepository;
import springWeb.demo.domain.Servicios.interfaces.UsuarioService;
import springWeb.demo.domain.exception.BusinessRuleException;
import springWeb.demo.domain.exception.ResourceNotFoundException;
import springWeb.demo.domain.exception.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new BusinessRuleException("Ya existe un usuario registrado con ese correo");
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setContraseña(passwordEncoder.encode(usuarioDTO.getContraseña()));

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
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario verificarUsuario(String email, String contraseña) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(contraseña, usuario.getContraseña())) {
            throw new UnauthorizedException("Credenciales incorrectas");
        }

        return usuario;
    }
}
