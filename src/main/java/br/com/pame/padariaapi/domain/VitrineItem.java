package br.com.pame.padariaapi.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "vitrine_item")
public class VitrineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_variacao_id", nullable = false)
    private ProdutoVariacao produtoVariacao;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(nullable = false)
    private Integer ordem = 0;

    // Vamos deixar o banco preencher (DEFAULT CURRENT_TIMESTAMP / ON UPDATE)
    @Column(name = "criado_em", insertable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", insertable = false, updatable = false)
    private LocalDateTime atualizadoEm;

    public VitrineItem() {}

    public Long getId() {
        return id;
    }

    public ProdutoVariacao getProdutoVariacao() {
        return produtoVariacao;
    }

    public void setProdutoVariacao(ProdutoVariacao produtoVariacao) {
        this.produtoVariacao = produtoVariacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }
}