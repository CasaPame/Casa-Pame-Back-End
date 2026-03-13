package br.com.pame.padariaapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.pame.padariaapi.dto.request.VitrineItemCreateRequestDTO;
import br.com.pame.padariaapi.dto.request.VitrineItemUpdateRequestDTO;
import br.com.pame.padariaapi.dto.response.VitrineItemResponseDTO;
import br.com.pame.padariaapi.service.VitrineService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/vitrine")
public class AdminVitrineController {

    private final VitrineService vitrineService;

    public AdminVitrineController(VitrineService vitrineService) {
        this.vitrineService = vitrineService;
    }

    @PostMapping("/favoritos") 
    public VitrineItemResponseDTO criar(@Valid @RequestBody VitrineItemCreateRequestDTO request) {
        return vitrineService.criar(request);
    }

    @PutMapping("/favoritos/{id}")
    public VitrineItemResponseDTO atualizar(@PathVariable Long id,
                                           @Valid @RequestBody VitrineItemUpdateRequestDTO request) {
        return vitrineService.atualizar(id, request);
    }

    @DeleteMapping("/favoritos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        vitrineService.remover(id);
    }
}