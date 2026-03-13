package br.com.pame.padariaapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pame.padariaapi.domain.VitrineItem;

public interface VitrineItemRepository extends JpaRepository<VitrineItem, Long> {

    Page<VitrineItem> findByAtivoTrue(Pageable pageable);

    boolean existsByProdutoVariacao_Id(Long produtoVariacaoId);
} 