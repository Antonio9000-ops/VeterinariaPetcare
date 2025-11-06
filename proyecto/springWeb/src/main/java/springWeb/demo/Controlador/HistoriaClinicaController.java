package springWeb.demo.Controlador;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springWeb.demo.domain.Dto.HistoriaClinicaDTO;
import springWeb.demo.domain.Dto.RecetaDTO;
import springWeb.demo.domain.Dto.TratamientoDTO;
import springWeb.demo.domain.Servicios.interfaces.HistoriaClinicaService;


@RestController
@RequestMapping("/api/historias")
@RequiredArgsConstructor
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;

    @PostMapping
public ResponseEntity<HistoriaClinicaDTO> crearHistoria(@RequestBody HistoriaClinicaDTO historiaClinicaDTO) {
    // El DTO ya viene con la descripción y el mascotaId gracias a @RequestBody
    HistoriaClinicaDTO nuevaHistoria = historiaClinicaService.guardarHistoria(historiaClinicaDTO);
    return ResponseEntity.ok(nuevaHistoria);
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

    @GetMapping("/tratamientos/mascota/{mascotaId}")
    public ResponseEntity<List<TratamientoDTO>> getTratamientosPorMascota(@PathVariable Long mascotaId) {
        List<TratamientoDTO> tratamientos = historiaClinicaService.listarTratamientosPorMascota(mascotaId);
        return ResponseEntity.ok(tratamientos);
    }

    @GetMapping("/recetas/mascota/{mascotaId}")
    public ResponseEntity<List<RecetaDTO>> getRecetasPorMascota(@PathVariable Long mascotaId) {
        List<RecetaDTO> recetas = historiaClinicaService.listarRecetasPorMascota(mascotaId);
        return ResponseEntity.ok(recetas);
    }
    
}
