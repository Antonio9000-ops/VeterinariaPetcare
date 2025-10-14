package springWeb.demo.Controlador;

import java.util.List;
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
import springWeb.demo.domain.Dto.HistoriaClinicaDTO;
import springWeb.demo.domain.Servicios.interfaces.HistoriaClinicaService;

@RestController
@RequestMapping("/api/historias")
@RequiredArgsConstructor
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;

    @PostMapping
    public ResponseEntity<HistoriaClinicaDTO> registrarEntrada(@RequestBody HistoriaClinicaDTO dto) {
        HistoriaClinicaDTO nuevaEntrada = historiaClinicaService.registrarEntrada(dto);
        return new ResponseEntity<>(nuevaEntrada, HttpStatus.CREATED);
    }

    @GetMapping("/mascota/{mascotaId}")
    public List<HistoriaClinicaDTO> listarPorMascota(@PathVariable Long mascotaId) {
        return historiaClinicaService.listarPorMascota(mascotaId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinicaDTO> obtenerPorId(@PathVariable Long id) {
        return historiaClinicaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrada(@PathVariable Long id) {
        historiaClinicaService.eliminarEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
