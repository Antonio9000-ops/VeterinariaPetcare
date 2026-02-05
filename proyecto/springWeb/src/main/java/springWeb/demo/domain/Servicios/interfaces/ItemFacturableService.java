package springWeb.demo.domain.Servicios.interfaces;

import java.util.List;
import java.util.Optional;
import springWeb.demo.domain.Modelos.ItemFacturable;

public interface ItemFacturableService {
    List<ItemFacturable> obtenerTodosLosItems();
    Optional<ItemFacturable> obtenerItemPorId(Long id);
    ItemFacturable crearItem(ItemFacturable item);
    ItemFacturable actualizarItem(Long id, ItemFacturable itemDetails);
    void eliminarItem(Long id);
}