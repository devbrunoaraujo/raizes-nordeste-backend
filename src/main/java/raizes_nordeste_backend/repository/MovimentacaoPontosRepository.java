package raizes_nordeste_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import raizes_nordeste_backend.model.MovimentacaoPontos;

public interface MovimentacaoPontosRepository extends JpaRepository<MovimentacaoPontos, Long> {

    Page<MovimentacaoPontos> findByContaFidelidadeIdOrderByCriadoEmDescIdDesc(
            Long contaFidelidadeId, Pageable pageable);
}
