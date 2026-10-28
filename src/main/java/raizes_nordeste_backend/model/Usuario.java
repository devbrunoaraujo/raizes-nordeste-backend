package raizes_nordeste_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import raizes_nordeste_backend.model.enums.PerfilUsuario;
import raizes_nordeste_backend.model.enums.StatusCadastro;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", nullable = false, length = 254, unique = true)
    private String email;

    @Column(name = "senha_hash", nullable = false, length = 255)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "perfil", nullable = false, length = 20)
    private PerfilUsuario perfil;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_unidade", nullable = true)
    private Unidade unidade;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "status", nullable = false, length = 10)
    private StatusCadastro status = StatusCadastro.ATIVO;

    @Column(name = "criado_em", nullable = false, columnDefinition = "DATETIME(3)", updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "participa_fidelidade", nullable = false)
    private boolean participaFidelidade;

    @Column(name = "aceita_marketing", nullable = false)
    private boolean aceitaMarketing;

    @Column(name = "fidelidade_aceita_em", nullable = true, columnDefinition = "DATETIME(3)")
    private LocalDateTime fidelidadeAceitaEm;

    @Column(name = "marketing_aceito_em", nullable = true, columnDefinition = "DATETIME(3)")
    private LocalDateTime marketingAceitoEm;

    @PrePersist
    protected void preencherDataCriacao() {
        if (criadoEm == null) {
            criadoEm = LocalDateTime.now().truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
        }
    }
}
