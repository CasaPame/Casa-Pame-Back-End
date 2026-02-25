package br.com.pame.padariaapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import br.com.pame.padariaapi.dto.request.PedidoCreateRequestDTO;
import br.com.pame.padariaapi.dto.response.PedidoResponseDTO;
import br.com.pame.padariaapi.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public PedidoResponseDTO criar(@Valid @RequestBody PedidoCreateRequestDTO request) {
        return pedidoService.criar(request);
    }

    @GetMapping
    public List<PedidoResponseDTO> listar() {
        return pedidoService.listarDoClienteLogado();
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorIdDoClienteLogado(id);
    }
}