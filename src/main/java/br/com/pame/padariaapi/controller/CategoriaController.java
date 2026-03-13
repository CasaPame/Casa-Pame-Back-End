package br.com.pame.padariaapi.controller;

import br.com.pame.padariaapi.domain.Categoria;
import br.com.pame.padariaapi.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Categoria> listar() {
        return service.listarTodas();
    }
} 