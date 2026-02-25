package br.com.pame.padariaapi.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.pame.padariaapi.domain.Cliente;
import br.com.pame.padariaapi.domain.Pedido;
import br.com.pame.padariaapi.domain.PedidoItem;
import br.com.pame.padariaapi.domain.ProdutoVariacao;
import br.com.pame.padariaapi.domain.TipoCliente;
import br.com.pame.padariaapi.dto.request.PedidoCreateRequestDTO;
import br.com.pame.padariaapi.dto.request.PedidoItemRequestDTO;
import br.com.pame.padariaapi.dto.response.PedidoItemResponseDTO;
import br.com.pame.padariaapi.dto.response.PedidoResponseDTO;
import br.com.pame.padariaapi.repository.PedidoRepository;
import br.com.pame.padariaapi.repository.ProdutoVariacaoRepository;
import br.com.pame.padariaapi.security.AuthService;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoVariacaoRepository produtoVariacaoRepository;
    private final AuthService authService;

    public PedidoService(PedidoRepository pedidoRepository,
                         ProdutoVariacaoRepository produtoVariacaoRepository,
                         AuthService authService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoVariacaoRepository = produtoVariacaoRepository;
        this.authService = authService;
    }

    public PedidoResponseDTO criar(PedidoCreateRequestDTO request) {
        if (request == null || request.getItens() == null || request.getItens().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido deve ter ao menos 1 item");
        }

        Cliente cliente = authService.getClienteLogado();

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setCriadoEm(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;
        List<PedidoItem> itens = new ArrayList<>();

        for (PedidoItemRequestDTO req : request.getItens()) {

            if (req.getProdutoVariacaoId() == null || req.getQuantidade() == null || req.getQuantidade() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Item inválido (produtoVariacaoId e quantidade > 0)");
            }

            ProdutoVariacao variacao = produtoVariacaoRepository.findById(req.getProdutoVariacaoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Variação não encontrada: " + req.getProdutoVariacaoId()));

            BigDecimal precoUnitario = (cliente.getTipoCliente() == TipoCliente.B2B)
                    ? variacao.getPrecoB2b()
                    : variacao.getPrecoB2c();

            if (precoUnitario == null) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "Preço não configurado para a variação: " + variacao.getId());
            }

            BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(req.getQuantidade()));

            PedidoItem item = new PedidoItem();
            item.setPedido(pedido);
            item.setProdutoVariacao(variacao);
            item.setQuantidade(req.getQuantidade());
            item.setPrecoUnitario(precoUnitario);
            item.setSubtotal(subtotal);

            itens.add(item);
            total = total.add(subtotal);
        }

        pedido.setItens(itens);
        pedido.setTotal(total);

        Pedido salvo = pedidoRepository.save(pedido);

        return toResponseDTO(salvo);
    }

    public List<PedidoResponseDTO> listarDoClienteLogado() {
        Cliente cliente = authService.getClienteLogado();

        List<Pedido> pedidos = pedidoRepository.findByClienteIdOrderByIdDesc(cliente.getId());

        List<PedidoResponseDTO> resp = new ArrayList<>();
        for (Pedido p : pedidos) {
            resp.add(toResponseDTO(p));
        }
        return resp;
    }

    public PedidoResponseDTO buscarPorIdDoClienteLogado(Long id) {
        Cliente cliente = authService.getClienteLogado();

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Pedido não encontrado: " + id));

        if (!pedido.getCliente().getId().equals(cliente.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado a este pedido");
        }

        return toResponseDTO(pedido);
    }

    private PedidoResponseDTO toResponseDTO(Pedido pedido) {
        List<PedidoItemResponseDTO> itensResp = new ArrayList<>();

        if (pedido.getItens() != null) {
            for (PedidoItem i : pedido.getItens()) {
                itensResp.add(new PedidoItemResponseDTO(
                        i.getId(),
                        i.getProdutoVariacao().getId(),
                        i.getProdutoVariacao().getDescricao(),
                        i.getQuantidade(),
                        i.getPrecoUnitario(),
                        i.getSubtotal()
                ));
            }
        }

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getCliente().getEmail(),
                pedido.getCliente().getTipoCliente().name(),
                pedido.getCriadoEm(),
                pedido.getTotal(),
                itensResp
        );
    }
}