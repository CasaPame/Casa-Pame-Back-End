package br.com.pame.padariaapi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private Boolean ativo = true;

    public Categoria() {}

    public Categoria(String nome) {
        this.nome = nome;
        this.ativo = true;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Boolean getAtivo() { return ativo; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}