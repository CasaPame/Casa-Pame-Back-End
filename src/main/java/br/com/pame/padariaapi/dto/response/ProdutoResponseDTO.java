package br.com.pame.padariaapi.dto.response;

import java.math.BigDecimal;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String sku;
    private String tipoVenda;

    private Long categoriaId;
    private String categoriaNome;

    private BigDecimal preco; // <- preço já “filtrado” pelo tipo do cliente logado

    public ProdutoResponseDTO() {}

    public ProdutoResponseDTO(Long id, String nome, String sku, String tipoVenda,
                              Long categoriaId, String categoriaNome,
                              BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.sku = sku;
        this.tipoVenda = tipoVenda;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getTipoVenda() { return tipoVenda; }
    public void setTipoVenda(String tipoVenda) { this.tipoVenda = tipoVenda; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }

    public String getCategoriaNome() { return categoriaNome; }
    public void setCategoriaNome(String categoriaNome) { this.categoriaNome = categoriaNome; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}