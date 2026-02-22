package br.com.pame.padariaapi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.pame.padariaapi.domain.Cliente;
import br.com.pame.padariaapi.domain.Produto;
import br.com.pame.padariaapi.domain.ProdutoVariacao;
import br.com.pame.padariaapi.dto.response.ProdutoResponseDTO;
import br.com.pame.padariaapi.dto.response.ProdutoVariacaoResponseDTO;
import br.com.pame.padariaapi.repository.ProdutoRepository;
import br.com.pame.padariaapi.repository.ProdutoVariacaoRepository;
import br.com.pame.padariaapi.security.AuthService;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoVariacaoRepository variacaoRepository;
    private final AuthService authService;

    public ProdutoService(ProdutoRepository produtoRepository,
                          ProdutoVariacaoRepository variacaoRepository,
                          AuthService authService) {
        this.produtoRepository = produtoRepository;
        this.variacaoRepository = variacaoRepository;
        this.authService = authService;
    }

    public List<ProdutoResponseDTO> listar() {
        Cliente cliente = authService.getClienteLogado();

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(p -> toDTO(p, cliente))
                .collect(Collectors.toList());
    }

    private ProdutoResponseDTO toDTO(Produto produto, Cliente cliente) {
        BigDecimal preco = calcularPreco(produto, cliente);

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getSku(),
                produto.getTipoVenda().name(),
                produto.getCategoria().getId(),
                produto.getCategoria().getNome(),
                preco
        );
    }

    /**
     * Regra simples agora:
     * - pega a PRIMEIRA variacao do produto (se existir)
     * - se cliente for B2B -> preco_b2b
     * - se cliente for B2C -> preco_b2c
     */
    private BigDecimal calcularPreco(Produto produto, Cliente cliente) {
        if (produto.getVariacoes() == null || produto.getVariacoes().isEmpty()) {
            return null;
        }

        ProdutoVariacao v = produto.getVariacoes().get(0);

        switch (cliente.getTipoCliente()) {
            case B2B:
                return v.getPrecoB2b();
            case B2C:
            default:
                return v.getPrecoB2c();
        }
    }

    /**
     * ✅ Endpoint /produtos/{id}/variacoes
     * Retorna todas as variações com o preço já calculado para o cliente logado.
     */
    public List<ProdutoVariacaoResponseDTO> listarVariacoesDTO(Long produtoId) {
        Cliente cliente = authService.getClienteLogado();

        List<ProdutoVariacao> variacoes = variacaoRepository.findByProdutoId(produtoId);

        return variacoes.stream()
                .map(v -> new ProdutoVariacaoResponseDTO(
                        v.getId(),
                        v.getDescricao(),
                        v.getPesoGramas(),
                        escolherPreco(v, cliente)
                ))
                .toList();
    }

    private BigDecimal escolherPreco(ProdutoVariacao v, Cliente cliente) {
        if (cliente.getTipoCliente() == br.com.pame.padariaapi.domain.TipoCliente.B2B) {
            return v.getPrecoB2b();
        }
        return v.getPrecoB2c();
    }
}