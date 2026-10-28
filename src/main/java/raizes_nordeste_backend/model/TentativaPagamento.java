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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import raizes_nordeste_backend.model.enums.StatusEstorno;
import raizes_nordeste_backend.model.enums.StatusPagamento;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tentativa_pagamento")
public class TentativaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @Column(name = "forma_pagamento", nullable = false, length = 20)
    private String formaPagamento;

    @Column(name = "valor", nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "status_pagamento", nullable = false, length = 20)
    private StatusPagamento statusPagamento = StatusPagamento.PENDENTE;

    @Column(name = "criado_em", nullable = false, columnDefinition = "DATETIME(3)", updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "respondido_em", nullable = true, columnDefinition = "DATETIME(3)")
    private LocalDateTime respondidoEm;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "status_estorno", nullable = false, length = 20)
    private StatusEstorno statusEstorno = StatusEstorno.NAO_SOLICITADO;

    @Column(name = "motivo_estorno", nullable = true, length = 255)
    private String motivoEstorno;

    @Column(name = "estorno_solicitado_em", nullable = true, columnDefinition = "DATETIME(3)")
    private LocalDateTime estornoSolicitadoEm;

    @Column(name = "estorno_concluido_em", nullable = true, columnDefinition = "DATETIME(3)")
    private LocalDateTime estornoConcluidoEm;

    @PrePersist
    protected void preencherDataCriacao() {
        if (criadoEm == null) {
            criadoEm = LocalDateTime.now().truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
        }
    }
}
