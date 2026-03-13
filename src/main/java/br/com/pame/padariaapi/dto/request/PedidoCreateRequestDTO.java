package br.com.pame.padariaapi.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class PedidoCreateRequestDTO {

    @NotEmpty(message = "Pedido deve ter ao menos 1 item")
    @Valid 
    private List<PedidoItemRequestDTO> itens;

    public PedidoCreateRequestDTO() {}

    public List<PedidoItemRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItemRequestDTO> itens) {
        this.itens = itens;
    }
}