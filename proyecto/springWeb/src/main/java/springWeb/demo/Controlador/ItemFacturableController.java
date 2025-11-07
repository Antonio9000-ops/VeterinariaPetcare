package springWeb.demo.Controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springWeb.demo.domain.Modelos.ItemFacturable;
import springWeb.demo.domain.Servicios.interfaces.ItemFacturableService;
import java.util.List;

@RestController
@RequestMapping("/api/items-facturables")
@RequiredArgsConstructor
public class ItemFacturableController {

    private final ItemFacturableService itemService;

    @GetMapping
    public List<ItemFacturable> getAllItems() {
        return itemService.obtenerTodosLosItems();
    }

    @PostMapping
    public ItemFacturable createItem(@RequestBody ItemFacturable item) {
        return itemService.crearItem(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemFacturable> updateItem(@PathVariable Long id, @RequestBody ItemFacturable itemDetails) {
        ItemFacturable updatedItem = itemService.actualizarItem(id, itemDetails);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.eliminarItem(id);
        return ResponseEntity.noContent().build();
    }
}