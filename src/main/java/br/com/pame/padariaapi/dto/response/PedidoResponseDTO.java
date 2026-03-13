package br.com.pame.padariaapi.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponseDTO {

    private Long id;
    private Long clienteId;
    private String clienteEmail;
    private String tipoCliente; 
    private LocalDateTime criadoEm;
    private BigDecimal total;
    private List<PedidoItemResponseDTO> itens;

    public PedidoResponseDTO(Long id, Long clienteId, String clienteEmail, String tipoCliente,
                             LocalDateTime criadoEm, BigDecimal total, List<PedidoItemResponseDTO> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.clienteEmail = clienteEmail;
        this.tipoCliente = tipoCliente;
        this.criadoEm = criadoEm;
        this.total = total;
        this.itens = itens;
    }

    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public String getClienteEmail() { return clienteEmail; }
    public String getTipoCliente() { return tipoCliente; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public BigDecimal getTotal() { return total; }
    public List<PedidoItemResponseDTO> getItens() { return itens; }
}