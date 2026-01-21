package springWeb.demo.domain.Servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springWeb.demo.domain.Modelos.ItemFacturable;
import springWeb.demo.domain.Repositorios.ItemFacturableRepository;
import springWeb.demo.domain.Servicios.interfaces.ItemFacturableService;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemFacturableServiceImpl implements ItemFacturableService {

    private final ItemFacturableRepository itemRepository;

    @Override
    public List<ItemFacturable> obtenerTodosLosItems() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<ItemFacturable> obtenerItemPorId(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public ItemFacturable crearItem(ItemFacturable item) {
        return itemRepository.save(item);
    }

    @Override
    public ItemFacturable actualizarItem(Long id, ItemFacturable itemDetails) {
        ItemFacturable item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado con id: " + id));

        item.setNombre(itemDetails.getNombre());
        item.setDescripcion(itemDetails.getDescripcion());
        item.setPrecio(itemDetails.getPrecio());
        item.setTipo(itemDetails.getTipo());

        return itemRepository.save(item);
    }

    @Override
    public void eliminarItem(Long id) {
        ItemFacturable item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado con id: " + id));
        itemRepository.delete(item);
    }
}