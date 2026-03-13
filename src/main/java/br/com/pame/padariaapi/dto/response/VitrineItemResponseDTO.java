package br.com.pame.padariaapi.dto.response;

import java.math.BigDecimal;

public class VitrineItemResponseDTO {

    private Long vitrineItemId;
    private Integer ordem; 

    private Long produtoId;
    private String produtoNome;

    private Long produtoVariacaoId;
    private String descricao;
    private Integer pesoGramas;

    private BigDecimal preco;

    public VitrineItemResponseDTO(Long vitrineItemId, Integer ordem,
                                  Long produtoId, String produtoNome,
                                  Long produtoVariacaoId, String descricao, Integer pesoGramas,
                                  BigDecimal preco) {
        this.vitrineItemId = vitrineItemId;
        this.ordem = ordem;
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.produtoVariacaoId = produtoVariacaoId;
        this.descricao = descricao;
        this.pesoGramas = pesoGramas;
        this.preco = preco;
    }

    public Long getVitrineItemId() {
        return vitrineItemId;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public Long getProdutoVariacaoId() {
        return produtoVariacaoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getPesoGramas() {
        return pesoGramas;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}