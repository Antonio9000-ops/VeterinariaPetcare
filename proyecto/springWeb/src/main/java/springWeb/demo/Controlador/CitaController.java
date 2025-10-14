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
import springWeb.demo.domain.Dto.CitaDTO;
import springWeb.demo.domain.Mapper.CitaMapper;
import springWeb.demo.domain.Servicios.interfaces.CitaService;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;
    private final CitaMapper citaMapper; // Mapper que vas a crear

    @PostMapping
    public ResponseEntity<CitaDTO> agendarCita(@RequestBody CitaDTO citaDTO) {
        CitaDTO nuevaCita = citaService.agendarCita(citaDTO);
        return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
    }

    @GetMapping("/veterinario/{veterinarioId}")
    public List<CitaDTO> listarCitasPorVeterinario(@PathVariable Long veterinarioId) {
        return citaService.listarCitasPorVeterinario(veterinarioId);
    }

    @GetMapping("/mascota/{mascotaId}")
    public List<CitaDTO> listarCitasPorMascota(@PathVariable Long mascotaId) {
        return citaService.listarCitasPorMascota(mascotaId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> obtenerCitaPorId(@PathVariable Long id) {
        return citaService.obtenerCitaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> actualizarCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) {
        return citaService.obtenerCitaPorId(id)
                .map(c -> {
                    citaDTO.setId(id);
                    CitaDTO actualizada = citaService.actualizarCita(citaDTO);
                    return new ResponseEntity<>(actualizada, HttpStatus.OK);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarCita(@PathVariable Long id) {
        citaService.cancelarCita(id);
        return ResponseEntity.noContent().build();
    }
}
