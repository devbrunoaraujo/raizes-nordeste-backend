package raizes_nordeste_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import raizes_nordeste_backend.model.Unidade;
import raizes_nordeste_backend.model.enums.StatusUnidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    Page<Unidade> findByStatus(StatusUnidade status, Pageable pageable);
}
