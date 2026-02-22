package br.com.pame.padariaapi.dto.response;

import java.math.BigDecimal;

public class ProdutoVariacaoResponseDTO {

    private Long id;
    private String descricao;
    private Integer pesoGramas;
    private BigDecimal preco; // <- preço já filtrado pelo login

    public ProdutoVariacaoResponseDTO(Long id, String descricao, Integer pesoGramas, BigDecimal preco) {
        this.id = id;
        this.descricao = descricao;
        this.pesoGramas = pesoGramas;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public Integer getPesoGramas() { return pesoGramas; }
    public BigDecimal getPreco() { return preco; }
}