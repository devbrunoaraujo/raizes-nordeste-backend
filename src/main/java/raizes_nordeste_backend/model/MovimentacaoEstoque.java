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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import raizes_nordeste_backend.model.enums.TipoMovimentacaoEstoque;

@Entity
@Table(name = "movimentacao_estoque")
public class MovimentacaoEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estoque_unidade", nullable = false)
    private EstoqueUnidade estoqueUnidade;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_funcionario", nullable = true)
    private Usuario funcionario;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_pedido", nullable = true)
    private Pedido pedido;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "tipo_movimentacao", nullable = false, length = 30)
    private TipoMovimentacaoEstoque tipoMovimentacao;

    @Column(name = "motivo", nullable = false, length = 255)
    private String motivo;

    @Column(name = "criado_em", nullable = false, columnDefinition = "DATETIME(3)", updatable = false)
    private LocalDateTime criadoEm;

    public MovimentacaoEstoque() {
    }

    public Long getId() {
        return id;
    }

    public EstoqueUnidade getEstoqueUnidade() {
        return estoqueUnidade;
    }

    public void setEstoqueUnidade(EstoqueUnidade estoqueUnidade) {
        this.estoqueUnidade = estoqueUnidade;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacaoEstoque getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacaoEstoque tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    @PrePersist
    protected void preencherDataCriacao() {
        if (criadoEm == null) {
            criadoEm = LocalDateTime.now().truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
        }
    }
}
