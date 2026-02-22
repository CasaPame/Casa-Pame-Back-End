package br.com.pame.padariaapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.pame.padariaapi.dto.response.ProdutoResponseDTO;
import br.com.pame.padariaapi.dto.response.ProdutoVariacaoResponseDTO;
import br.com.pame.padariaapi.service.ProdutoService;

@RestController
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/produtos")
    public List<ProdutoResponseDTO> listar() {
        return produtoService.listar();
    }

    @GetMapping("/produtos/{id}/variacoes")
    public List<ProdutoVariacaoResponseDTO> listarVariacoes(@PathVariable Long id) {
        return produtoService.listarVariacoesDTO(id);
    }
}