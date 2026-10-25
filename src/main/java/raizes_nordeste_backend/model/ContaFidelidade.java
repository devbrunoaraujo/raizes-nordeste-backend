package raizes_nordeste_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "conta_fidelidade")
public class ContaFidelidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false, unique = true)
    private Usuario cliente;

    @Column(name = "saldo_total", nullable = false)
    private Integer saldoTotal = 0;

    @Column(name = "saldo_reservado", nullable = false)
    private Integer saldoReservado = 0;

    public ContaFidelidade() {
    }

    public Long getId() {
        return id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Integer getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(Integer saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public Integer getSaldoReservado() {
        return saldoReservado;
    }

    public void setSaldoReservado(Integer saldoReservado) {
        this.saldoReservado = saldoReservado;
    }

    @Transient
    public int getSaldoDisponivel() {
        return saldoTotal - saldoReservado;
    }
}
