package com.example.revenda_veiculos_postech.application.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VendaRequest {
    @NotNull
    @Positive
    private Long veiculoId;

    public Long getVeiculoId() { return veiculoId; }
    public void setVeiculoId(Long veiculoId) { this.veiculoId = veiculoId; }
}
