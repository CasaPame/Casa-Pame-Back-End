package br.com.pame.padariaapi.repository;

import br.com.pame.padariaapi.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}