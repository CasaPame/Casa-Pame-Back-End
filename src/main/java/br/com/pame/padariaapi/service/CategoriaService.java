package br.com.pame.padariaapi.service;

import br.com.pame.padariaapi.domain.Categoria;
import br.com.pame.padariaapi.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listarTodas() {
        return repository.findAll();
    }
} 