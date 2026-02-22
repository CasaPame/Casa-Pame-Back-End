package br.com.pame.padariaapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // cliente que fez o pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    private String status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoItem> itens = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
        this.status = "CRIADO";
    }
}
