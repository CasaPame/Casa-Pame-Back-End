package br.com.pame.padariaapi.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto_variacao")
public class ProdutoVariacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(nullable = false, length = 120)
    private String descricao;

    // Para variações FIXAS (ex: 100g, 200g, 300g). Pode ser null quando for "peso livre"
    @Column(name = "peso_gramas")
    private Integer pesoGramas;

    @Column(name = "preco_b2c", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoB2c;

    @Column(name = "preco_b2b", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoB2b;

    public ProdutoVariacao() {}

    public Long getId() { return id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getPesoGramas() { return pesoGramas; }
    public void setPesoGramas(Integer pesoGramas) { this.pesoGramas = pesoGramas; }

    public BigDecimal getPrecoB2c() { return precoB2c; }
    public void setPrecoB2c(BigDecimal precoB2c) { this.precoB2c = precoB2c; }

    public BigDecimal getPrecoB2b() { return precoB2b; }
    public void setPrecoB2b(BigDecimal precoB2b) { this.precoB2b = precoB2b; }
}