package springWeb.demo.Controlador;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springWeb.demo.domain.Dto.MascotaDTO;
import springWeb.demo.domain.Servicios.interfaces.MascotaService;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaService mascotaService;

    @PostMapping
    public ResponseEntity<MascotaDTO> registrarMascota(@RequestBody MascotaDTO mascotaDTO) {
        MascotaDTO nuevaMascota = mascotaService.registrarMascota(mascotaDTO);
        return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED);
    }

    @GetMapping("/dueno/{duenoId}")
    public List<MascotaDTO> listarMascotasPorDueño(@PathVariable Long duenoId) {
        return mascotaService.listarMascotasPorDueño(duenoId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> obtenerMascotaPorId(@PathVariable Long id) {
        return mascotaService.obtenerMascotaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaDTO> actualizarMascota(@PathVariable Long id, @RequestBody MascotaDTO mascotaDTO) {
        return mascotaService.obtenerMascotaPorId(id)
                .map(m -> {
                    mascotaDTO.setId(id);
                    MascotaDTO actualizada = mascotaService.actualizarMascota(mascotaDTO);
                    return new ResponseEntity<>(actualizada, HttpStatus.OK);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }
}
