package br.com.pame.padariaapi.dto.response;

import java.math.BigDecimal;

public class PedidoItemResponseDTO {

    private Long id;
    private Long produtoVariacaoId;
    private String descricao;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    public PedidoItemResponseDTO(Long id, Long produtoVariacaoId, String descricao, Integer quantidade,
                                 BigDecimal precoUnitario, BigDecimal subtotal) {
        this.id = id;
        this.produtoVariacaoId = produtoVariacaoId;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = subtotal;
    }

    public Long getId() { return id; }
    public Long getProdutoVariacaoId() { return produtoVariacaoId; }
    public String getDescricao() { return descricao; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
}