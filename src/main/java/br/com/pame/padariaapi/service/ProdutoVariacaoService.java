package br.com.pame.padariaapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.pame.padariaapi.dto.response.ProdutoVariacaoResponseDTO;
import br.com.pame.padariaapi.repository.ProdutoVariacaoRepository;

@Service
public class ProdutoVariacaoService {

private final ProdutoVariacaoRepository repository;

public ProdutoVariacaoService(ProdutoVariacaoRepository repository) {
    this.repository = repository;
}

public List<ProdutoVariacaoResponseDTO> buscarPorProduto(Long produtoId) {

    return repository.findByProdutoId(produtoId)
            .stream()
            .map(v -> new ProdutoVariacaoResponseDTO(
                    v.getId(),
                    v.getDescricao(),
                    v.getPesoGramas(),
                    v.getPrecoB2c()
            ))
            .collect(Collectors.toList());
}
 
}
