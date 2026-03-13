package br.com.pame.padariaapi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class VitrineItemUpdateRequestDTO {

    @NotNull(message = "ordem é obrigatória")
    @Min(value = 0, message = "ordem deve ser >= 0")
    private Integer ordem; 

    @NotNull(message = "ativo é obrigatório")
    private Boolean ativo;

    public VitrineItemUpdateRequestDTO() {}

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}