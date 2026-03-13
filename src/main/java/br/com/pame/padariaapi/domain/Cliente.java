package br.com.pame.padariaapi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false) 
    private String senha; // BCrypt

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TipoCliente tipoCliente; // B2C ou B2B

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role = Role.CLIENTE; // CLIENTE ou ADMIN

    public Cliente() {}

    public Cliente(String email, String senha, TipoCliente tipoCliente, Role role) {
        this.email = email;
        this.senha = senha;
        this.tipoCliente = tipoCliente;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public TipoCliente getTipoCliente() { return tipoCliente; }
    public Role getRole() { return role; }

    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setTipoCliente(TipoCliente tipoCliente) { this.tipoCliente = tipoCliente; }
    public void setRole(Role role) { this.role = role; }
}