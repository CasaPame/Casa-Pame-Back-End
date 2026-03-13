package br.com.pame.padariaapi.domain;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_item")
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido; 

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_variacao_id")
    private ProdutoVariacao produtoVariacao;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false, precision = 15, scale = 2)
    private BigDecimal precoUnitario;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal subtotal;

    public PedidoItem() {}

    public Long getId() { return id; }
    public Pedido getPedido() { return pedido; }
    public ProdutoVariacao getProdutoVariacao() { return produtoVariacao; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }

    public void setId(Long id) { this.id = id; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public void setProdutoVariacao(ProdutoVariacao produtoVariacao) { this.produtoVariacao = produtoVariacao; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}