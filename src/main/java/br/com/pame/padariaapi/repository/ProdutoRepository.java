package br.com.pame.padariaapi.repository;

import br.com.pame.padariaapi.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsBySkuIgnoreCase(String sku);

    @Query("""
        select p from Produto p
        where (:search is null or :search = '' or
               lower(p.nome) like lower(concat('%', :search, '%')) or
               lower(p.sku)  like lower(concat('%', :search, '%')))
          and (:categoriaId is null or p.categoria.id = :categoriaId)
          and (:ativo is null or p.ativo = :ativo)
    """)
    Page<Produto> buscarAdmin(
            Pageable pageable,
            @Param("search") String search,
            @Param("categoriaId") Long categoriaId,
            @Param("ativo") Boolean ativo
    );
}