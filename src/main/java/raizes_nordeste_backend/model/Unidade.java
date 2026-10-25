package raizes_nordeste_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import raizes_nordeste_backend.model.enums.StatusUnidade;

@Entity
@Table(name = "unidade")
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "endereco", nullable = false, length = 255)
    private String endereco;

    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "status", nullable = false, length = 10)
    private StatusUnidade status = StatusUnidade.ATIVA;

    public Unidade() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public StatusUnidade getStatus() {
        return status;
    }

    public void setStatus(StatusUnidade status) {
        this.status = status;
    }
}
