package br.com.pame.padariaapi.repository;

import br.com.pame.padariaapi.domain.ProdutoVariacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoVariacaoRepository extends JpaRepository<ProdutoVariacao, Long> {
    List<ProdutoVariacao> findByProdutoId(Long produtoId);
} 