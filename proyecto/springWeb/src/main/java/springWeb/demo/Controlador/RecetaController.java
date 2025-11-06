package springWeb.demo.Controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springWeb.demo.domain.Dto.RecetaDTO;
import springWeb.demo.domain.Servicios.interfaces.RecetaService; // Asegúrate de crear este servicio

@RestController
@RequestMapping("/api/recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @PostMapping
    public ResponseEntity<RecetaDTO> crearReceta(@RequestBody RecetaDTO recetaDTO) {
        RecetaDTO nuevaReceta = recetaService.guardarReceta(recetaDTO);
        return ResponseEntity.ok(nuevaReceta);
    }
}