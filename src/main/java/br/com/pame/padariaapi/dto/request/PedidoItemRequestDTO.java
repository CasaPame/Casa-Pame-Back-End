package br.com.pame.padariaapi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PedidoItemRequestDTO {

    @NotNull(message = "produtoVariacaoId é obrigatório")
    private Long produtoVariacaoId;

    @NotNull(message = "quantidade é obrigatória")
    @Min(value = 1, message = "quantidade deve ser maior que zero")
    private Integer quantidade;

    public PedidoItemRequestDTO() {}

    public Long getProdutoVariacaoId() {
        return produtoVariacaoId;
    }

    public void setProdutoVariacaoId(Long produtoVariacaoId) {
        this.produtoVariacaoId = produtoVariacaoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}