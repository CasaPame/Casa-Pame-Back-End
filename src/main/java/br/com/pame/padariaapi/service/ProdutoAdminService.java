package br.com.pame.padariaapi.service;

import br.com.pame.padariaapi.domain.Categoria;
import br.com.pame.padariaapi.domain.Produto;
import br.com.pame.padariaapi.dto.request.ProdutoCreateRequestDTO;
import br.com.pame.padariaapi.dto.request.ProdutoUpdateRequestDTO;
import br.com.pame.padariaapi.dto.response.AdminProdutoResponseDTO;
import br.com.pame.padariaapi.repository.CategoriaRepository;
import br.com.pame.padariaapi.repository.ProdutoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoAdminService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoAdminService(ProdutoRepository produtoRepository,
                               CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public AdminProdutoResponseDTO criar(ProdutoCreateRequestDTO request) {
        String sku = request.getSku().trim();

        if (produtoRepository.existsBySkuIgnoreCase(sku)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "SKU já cadastrado: " + sku);
        }

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria não encontrada: " + request.getCategoriaId()));

        Produto p = new Produto();
        p.setNome(request.getNome().trim());
        p.setSku(sku);
        p.setCategoria(categoria);
        p.setTipoVenda(request.getTipoVenda()); // ✅ agora é enum
        p.setAtivo(request.getAtivo() != null ? request.getAtivo() : true);

        Produto salvo = produtoRepository.save(p);
        return toAdminDTO(salvo);
    }

    public AdminProdutoResponseDTO atualizar(Long id, ProdutoUpdateRequestDTO request) {
        Produto p = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado: " + id));

        String novoSku = request.getSku().trim();

        if (!p.getSku().equalsIgnoreCase(novoSku)
                && produtoRepository.existsBySkuIgnoreCase(novoSku)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "SKU já cadastrado: " + novoSku);
        }

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria não encontrada: " + request.getCategoriaId()));

        p.setNome(request.getNome().trim());
        p.setSku(novoSku);
        p.setCategoria(categoria);
        p.setTipoVenda(request.getTipoVenda()); // ✅ enum
        p.setAtivo(request.getAtivo());

        Produto salvo = produtoRepository.save(p);
        return toAdminDTO(salvo);
    }

    public Page<AdminProdutoResponseDTO> listar(Pageable pageable, String search, Long categoriaId, Boolean ativo) {
        String s = (search == null ? null : search.trim());
        Page<Produto> page = produtoRepository.buscarAdmin(pageable, s, categoriaId, ativo);
        return page.map(this::toAdminDTO);
    }

    private AdminProdutoResponseDTO toAdminDTO(Produto p) {
        return new AdminProdutoResponseDTO(
                p.getId(),
                p.getNome(),
                p.getSku(),
                p.getTipoVenda() != null ? p.getTipoVenda().name() : null,
                p.getCategoria() != null ? p.getCategoria().getId() : null,
                p.getCategoria() != null ? p.getCategoria().getNome() : null,
                p.getAtivo()
        );
    }
}