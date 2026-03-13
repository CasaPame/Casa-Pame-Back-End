package br.com.pame.padariaapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import br.com.pame.padariaapi.dto.response.VitrineItemResponseDTO;
import br.com.pame.padariaapi.service.VitrineService;

@RestController
@RequestMapping("/vitrine")
public class VitrineController {

    private final VitrineService vitrineService;

    public VitrineController(VitrineService vitrineService) {
        this.vitrineService = vitrineService;
    } 

    @GetMapping("/favoritos")
    public Page<VitrineItemResponseDTO> listarFavoritos(
            @PageableDefault(size = 10, sort = "ordem") Pageable pageable
    ) {
        return vitrineService.listarFavoritos(pageable);
    }
}