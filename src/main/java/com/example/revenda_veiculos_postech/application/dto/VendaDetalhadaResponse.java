package com.example.revenda_veiculos_postech.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VendaDetalhadaResponse {
    private Long id;
    private String userCognito;
    private BigDecimal valorVenda;
    private LocalDateTime dataVenda;
    private VeiculoResponse veiculo;

    public VendaDetalhadaResponse(Long id, String userCognito, BigDecimal valorVenda, LocalDateTime dataVenda, VeiculoResponse veiculo) {
        this.id = id;
        this.userCognito = userCognito;
        this.valorVenda = valorVenda;
        this.dataVenda = dataVenda;
        this.veiculo = veiculo;
    }

    public Long getId() { return id; }
    public String getUserCognito() { return userCognito; }
    public BigDecimal getValorVenda() { return valorVenda; }
    public LocalDateTime getDataVenda() { return dataVenda; }
    public VeiculoResponse getVeiculo() { return veiculo; }
}
