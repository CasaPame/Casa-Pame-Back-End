package br.com.pame.padariaapi.controller;

import br.com.pame.padariaapi.dto.request.ProdutoCreateRequestDTO;
import br.com.pame.padariaapi.dto.request.ProdutoUpdateRequestDTO;
import br.com.pame.padariaapi.dto.response.AdminProdutoResponseDTO;
import br.com.pame.padariaapi.service.ProdutoAdminService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/produtos")
public class AdminProdutoController {

    private final ProdutoAdminService produtoAdminService;

    public AdminProdutoController(ProdutoAdminService produtoAdminService) {
        this.produtoAdminService = produtoAdminService;
    }

    @PostMapping 
    public AdminProdutoResponseDTO criar(@Valid @RequestBody ProdutoCreateRequestDTO request) {
        return produtoAdminService.criar(request);
    }

    @PutMapping("/{id}")
    public AdminProdutoResponseDTO atualizar(@PathVariable Long id,
                                            @Valid @RequestBody ProdutoUpdateRequestDTO request) {
        return produtoAdminService.atualizar(id, request);
    }

    @GetMapping
    public Page<AdminProdutoResponseDTO> listar(Pageable pageable,
                                               @RequestParam(required = false) String search,
                                               @RequestParam(required = false) Long categoriaId,
                                               @RequestParam(required = false) Boolean ativo) {
        return produtoAdminService.listar(pageable, search, categoriaId, ativo);
    }
}