package raizes_nordeste_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import raizes_nordeste_backend.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    List<ItemPedido> findByPedidoIdOrderByIdAsc(Long pedidoId);
}
