package raizes_nordeste_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "estoque_unidade", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_unidade", "id_produto"})
})
public class EstoqueUnidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_unidade", nullable = false)
    private Unidade unidade;

    @Column(name = "estoque_total", nullable = false)
    private Integer estoqueTotal = 0;

    @Column(name = "estoque_reservado", nullable = false)
    private Integer estoqueReservado = 0;

    public EstoqueUnidade() {
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Integer getEstoqueTotal() {
        return estoqueTotal;
    }

    public void setEstoqueTotal(Integer estoqueTotal) {
        this.estoqueTotal = estoqueTotal;
    }

    public Integer getEstoqueReservado() {
        return estoqueReservado;
    }

    public void setEstoqueReservado(Integer estoqueReservado) {
        this.estoqueReservado = estoqueReservado;
    }

    @Transient
    public int getEstoqueDisponivel() {
        return estoqueTotal - estoqueReservado;
    }
}
