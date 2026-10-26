package raizes_nordeste_backend.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import raizes_nordeste_backend.model.EstoqueUnidade;

public interface EstoqueUnidadeRepository extends JpaRepository<EstoqueUnidade, Long> {

    Optional<EstoqueUnidade> findByUnidadeIdAndProdutoId(Long unidadeId, Long produtoId);

    Page<EstoqueUnidade> findByUnidadeId(Long unidadeId, Pageable pageable);
}
