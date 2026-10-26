package raizes_nordeste_backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import raizes_nordeste_backend.model.ContaFidelidade;

public interface ContaFidelidadeRepository extends JpaRepository<ContaFidelidade, Long> {

    Optional<ContaFidelidade> findByClienteId(Long clienteId);
}
