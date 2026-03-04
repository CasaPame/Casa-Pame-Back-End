package br.com.pame.padariaapi.dto.request;

import br.com.pame.padariaapi.domain.TipoVenda;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoUpdateRequestDTO {

    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @NotBlank(message = "sku é obrigatório")
    private String sku;

    @NotNull(message = "categoriaId é obrigatório")
    private Long categoriaId;

    @NotNull(message = "tipoVenda é obrigatório")
    private TipoVenda tipoVenda; // ✅ enum

    @NotNull(message = "ativo é obrigatório")
    private Boolean ativo;

    public ProdutoUpdateRequestDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }

    public TipoVenda getTipoVenda() { return tipoVenda; }
    public void setTipoVenda(TipoVenda tipoVenda) { this.tipoVenda = tipoVenda; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}