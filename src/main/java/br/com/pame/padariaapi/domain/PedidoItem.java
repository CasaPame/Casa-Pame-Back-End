package br.com.pame.padariaapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pedido_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // qual pedido esse item pertence
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    // qual variação foi comprada
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_variacao_id", nullable = false)
    private ProdutoVariacao produtoVariacao;

    private Integer quantidade;

    @Column(name = "preco_unitario")  
    private BigDecimal precoUnitario;

    private BigDecimal subtotal;  
}
