package br.com.pame.padariaapi.repository;

import br.com.pame.padariaapi.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaId(Long categoriaId);

    @Query("""
        select p from Produto p
        where lower(p.nome) like lower(concat('%', :search, '%'))
           or lower(p.sku)  like lower(concat('%', :search, '%'))
    """)
    List<Produto> searchByNomeOuSku(String search);
}