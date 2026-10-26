package raizes_nordeste_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import raizes_nordeste_backend.model.Produto;
import raizes_nordeste_backend.model.enums.StatusCadastro;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Page<Produto> findByStatus(StatusCadastro status, Pageable pageable);
}
