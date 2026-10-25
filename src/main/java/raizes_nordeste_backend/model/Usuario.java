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
import raizes_nordeste_backend.model.enums.PerfilUsuario;
import raizes_nordeste_backend.model.enums.StatusCadastro;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Usuario() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public StatusCadastro getStatus() {
        return status;
    }

    public void setStatus(StatusCadastro status) {
        this.status = status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public boolean isParticipaFidelidade() {
        return participaFidelidade;
    }

    public void setParticipaFidelidade(boolean participaFidelidade) {
        this.participaFidelidade = participaFidelidade;
    }

    public boolean isAceitaMarketing() {
        return aceitaMarketing;
    }

    public void setAceitaMarketing(boolean aceitaMarketing) {
        this.aceitaMarketing = aceitaMarketing;
    }

    public LocalDateTime getFidelidadeAceitaEm() {
        return fidelidadeAceitaEm;
    }

    public void setFidelidadeAceitaEm(LocalDateTime fidelidadeAceitaEm) {
        this.fidelidadeAceitaEm = fidelidadeAceitaEm;
    }

    public LocalDateTime getMarketingAceitoEm() {
        return marketingAceitoEm;
    }

    public void setMarketingAceitoEm(LocalDateTime marketingAceitoEm) {
        this.marketingAceitoEm = marketingAceitoEm;
    }

    @PrePersist
    protected void preencherDataCriacao() {
        if (criadoEm == null) {
            criadoEm = LocalDateTime.now().truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
        }
    }
}
