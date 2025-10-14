package springWeb.demo.domain.Mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import springWeb.demo.domain.Dto.UsuarioDTO;
import springWeb.demo.domain.Modelos.Usuario;

@Component
public class UsuarioMapper {

        // De Usuario a UsuarioDTO
        public UsuarioDTO toDTO(Usuario usuario) {
                if (usuario == null)
                        return null;

                return UsuarioDTO.builder()
                                .id(usuario.getId())
                                .nombre(usuario.getNombre())
                                .email(usuario.getEmail())
                                .rol(usuario.getRol())
                                .contraseña(usuario.getContraseña())
                                // Convertir relaciones a IDs o nombres
                                .mascotas(usuario.getMascotas() != null
                                                ? usuario.getMascotas().stream()
                                                                .map(m -> m.getNombre())
                                                                .toList()
                                                : List.of())
                                .citasAsignadas(usuario.getCitasAsignadas() != null
                                                ? usuario.getCitasAsignadas().stream()
                                                                .map(c -> c.getId())
                                                                .toList()
                                                : List.of())
                                .build();
        }

        // De UsuarioDTO a Usuario
        public Usuario toEntity(UsuarioDTO dto) {
                if (dto == null)
                        return null;

                return Usuario.builder()
                                .id(dto.getId())
                                .nombre(dto.getNombre())
                                .email(dto.getEmail())
                                .rol(dto.getRol())
                                .contraseña(dto.getContraseña())
                                // Relaciones se dejan como null o se llenan después en el service
                                .mascotas(null)
                                .citasAsignadas(null)
                                .build();
        }
}
