package br.com.pame.padariaapi.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 50, unique = true)
    private String sku;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_venda", nullable = false, length = 20)
    private TipoVenda tipoVenda;

    @Column(nullable = false)
    private Boolean ativo = true;

    @OneToMany(
            mappedBy = "produto",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ProdutoVariacao> variacoes = new ArrayList<>();

    public Produto() {}

    // ================= GETTERS =================

    public Long getId() { return id; }

    public String getNome() { return nome; }

    public String getSku() { return sku; }

    public Categoria getCategoria() { return categoria; }

    public TipoVenda getTipoVenda() { return tipoVenda; }

    public Boolean getAtivo() { return ativo; }

    public List<ProdutoVariacao> getVariacoes() { return variacoes; }

    // ================= SETTERS =================

    public void setId(Long id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    public void setSku(String sku) { this.sku = sku; }

    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public void setTipoVenda(TipoVenda tipoVenda) { this.tipoVenda = tipoVenda; }

    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public void setVariacoes(List<ProdutoVariacao> variacoes) {
        this.variacoes = variacoes;
    }

    // ================= MÉTODOS AUXILIARES =================

    public void addVariacao(ProdutoVariacao variacao) {
        variacoes.add(variacao);
        variacao.setProduto(this);
    }

    public void removeVariacao(ProdutoVariacao variacao) {
        variacoes.remove(variacao);
        variacao.setProduto(null);
    }
}