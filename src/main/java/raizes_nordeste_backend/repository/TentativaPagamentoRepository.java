package raizes_nordeste_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import raizes_nordeste_backend.model.TentativaPagamento;

public interface TentativaPagamentoRepository extends JpaRepository<TentativaPagamento, Long> {

    List<TentativaPagamento> findByPedidoIdOrderByCriadoEmDescIdDesc(Long pedidoId);
}
