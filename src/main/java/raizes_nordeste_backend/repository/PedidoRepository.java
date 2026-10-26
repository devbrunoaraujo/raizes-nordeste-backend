package raizes_nordeste_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import raizes_nordeste_backend.model.Pedido;
import raizes_nordeste_backend.model.enums.StatusPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Page<Pedido> findByClienteIdOrderByCriadoEmDescIdDesc(Long clienteId, Pageable pageable);

    Page<Pedido> findByUnidadeIdOrderByCriadoEmDescIdDesc(Long unidadeId, Pageable pageable);

    Page<Pedido> findByUnidadeIdAndStatusOrderByCriadoEmAscIdAsc(
            Long unidadeId, StatusPedido status, Pageable pageable);
}
