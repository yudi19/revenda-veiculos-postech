package com.example.revenda_veiculos_postech.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VendaResponse {
    private Long id;
    private Long veiculoId;
    private String userCognito;
    private BigDecimal valorVenda;
    private LocalDateTime dataVenda;

    public VendaResponse(Long id, Long veiculoId, String userCognito, BigDecimal valorVenda, LocalDateTime dataVenda) {
        this.id = id;
        this.veiculoId = veiculoId;
        this.userCognito = userCognito;
        this.valorVenda = valorVenda;
        this.dataVenda = dataVenda;
    }

    public Long getId() { return id; }
    public Long getVeiculoId() { return veiculoId; }
    public String getUserCognito() { return userCognito; }
    public BigDecimal getValorVenda() { return valorVenda; }
    public LocalDateTime getDataVenda() { return dataVenda; }
}
