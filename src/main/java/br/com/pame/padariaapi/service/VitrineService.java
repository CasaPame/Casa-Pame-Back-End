package br.com.pame.padariaapi.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.pame.padariaapi.domain.Cliente;
import br.com.pame.padariaapi.domain.ProdutoVariacao;
import br.com.pame.padariaapi.domain.TipoCliente;
import br.com.pame.padariaapi.domain.VitrineItem;
import br.com.pame.padariaapi.dto.request.VitrineItemCreateRequestDTO;
import br.com.pame.padariaapi.dto.request.VitrineItemUpdateRequestDTO;
import br.com.pame.padariaapi.dto.response.VitrineItemResponseDTO;
import br.com.pame.padariaapi.repository.ProdutoVariacaoRepository;
import br.com.pame.padariaapi.repository.VitrineItemRepository;
import br.com.pame.padariaapi.security.AuthService;

@Service
public class VitrineService {

    private final VitrineItemRepository vitrineItemRepository;
    private final ProdutoVariacaoRepository produtoVariacaoRepository;
    private final AuthService authService;

    public VitrineService(VitrineItemRepository vitrineItemRepository,
                          ProdutoVariacaoRepository produtoVariacaoRepository,
                          AuthService authService) {
        this.vitrineItemRepository = vitrineItemRepository;
        this.produtoVariacaoRepository = produtoVariacaoRepository;
        this.authService = authService;
    }

    // ✅ público (sem login). Se tiver login, filtra preço; se não, assume B2C.
    public Page<VitrineItemResponseDTO> listarFavoritos(Pageable pageable) {
        Cliente cliente = authService.getClienteLogadoSeExistir(); // <-- AQUI
        return vitrineItemRepository.findByAtivoTrue(pageable)
                .map(item -> toDTO(item, cliente));
    }

    // admin - criar item
    public VitrineItemResponseDTO criar(VitrineItemCreateRequestDTO request) {
        ProdutoVariacao variacao = produtoVariacaoRepository.findById(request.getProdutoVariacaoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Variação não encontrada: " + request.getProdutoVariacaoId()));

        if (vitrineItemRepository.existsByProdutoVariacao_Id(variacao.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Esta variação já está na vitrine: " + variacao.getId());
        }

        VitrineItem item = new VitrineItem();
        item.setProdutoVariacao(variacao);
        item.setOrdem(request.getOrdem());
        item.setAtivo(request.getAtivo());

        VitrineItem salvo = vitrineItemRepository.save(item);

        return toDTO(salvo, null); // admin -> assume B2C no retorno
    }

    // admin - atualizar
    public VitrineItemResponseDTO atualizar(Long id, VitrineItemUpdateRequestDTO request) {
        VitrineItem item = vitrineItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Item de vitrine não encontrado: " + id));

        item.setOrdem(request.getOrdem());
        item.setAtivo(request.getAtivo());

        VitrineItem salvo = vitrineItemRepository.save(item);

        return toDTO(salvo, null);
    }

    // admin - remover
    public void remover(Long id) {
        VitrineItem item = vitrineItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Item de vitrine não encontrado: " + id));

        vitrineItemRepository.delete(item);
    }

    private VitrineItemResponseDTO toDTO(VitrineItem item, Cliente cliente) {
        ProdutoVariacao v = item.getProdutoVariacao();

        boolean isB2b = (cliente != null && cliente.getTipoCliente() == TipoCliente.B2B);
        BigDecimal preco = isB2b ? v.getPrecoB2b() : v.getPrecoB2c();

        Long produtoId = (v.getProduto() != null ? v.getProduto().getId() : null);
        String produtoNome = (v.getProduto() != null ? v.getProduto().getNome() : null);

        return new VitrineItemResponseDTO(
                item.getId(),
                item.getOrdem(),
                produtoId,
                produtoNome,
                v.getId(),
                v.getDescricao(),
                v.getPesoGramas(),
                preco
        );
    }
}