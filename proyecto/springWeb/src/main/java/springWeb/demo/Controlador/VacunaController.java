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
import springWeb.demo.domain.Dto.VacunaDTO;
import springWeb.demo.domain.Mapper.VacunaMapper;
import springWeb.demo.domain.Servicios.interfaces.VacunaService;

@RestController
@RequestMapping("/api/vacunas")
@RequiredArgsConstructor
public class VacunaController {

    private final VacunaService vacunaService;
    private final VacunaMapper vacunaMapper;

    @PostMapping
    public ResponseEntity<VacunaDTO> registrarVacuna(@RequestBody VacunaDTO vacunaDTO) {
        VacunaDTO nuevaVacuna = vacunaService.registrarVacuna(vacunaDTO);
        return new ResponseEntity<>(nuevaVacuna, HttpStatus.CREATED);
    }

    @GetMapping("/mascota/{mascotaId}")
    public List<VacunaDTO> listarVacunasPorMascota(@PathVariable Long mascotaId) {
        return vacunaService.listarVacunasPorMascota(mascotaId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacunaDTO> obtenerVacunaPorId(@PathVariable Long id) {
        return vacunaService.obtenerVacunaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVacuna(@PathVariable Long id) {
        vacunaService.eliminarVacuna(id);
        return ResponseEntity.noContent().build();
    }
}
