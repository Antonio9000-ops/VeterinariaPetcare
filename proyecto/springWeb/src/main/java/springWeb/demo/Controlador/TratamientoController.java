package springWeb.demo.Controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springWeb.demo.domain.Dto.TratamientoDTO;
import springWeb.demo.domain.Servicios.interfaces.TratamientoService; 

@RestController
@RequestMapping("/api/tratamientos")
@RequiredArgsConstructor
public class TratamientoController {

    private final TratamientoService tratamientoService;

    @PostMapping
    public ResponseEntity<TratamientoDTO> crearTratamiento(@RequestBody TratamientoDTO tratamientoDTO) {
        TratamientoDTO nuevoTratamiento = tratamientoService.guardarTratamiento(tratamientoDTO);
        return ResponseEntity.ok(nuevoTratamiento);
    }
}