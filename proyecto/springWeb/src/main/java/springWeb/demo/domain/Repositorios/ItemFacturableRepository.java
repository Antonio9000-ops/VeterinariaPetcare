package springWeb.demo.domain.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import springWeb.demo.domain.Modelos.ItemFacturable;

public interface ItemFacturableRepository extends JpaRepository<ItemFacturable, Long> {
}