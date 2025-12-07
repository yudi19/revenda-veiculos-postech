package com.example.revenda_veiculos_postech.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Venda {
    private Long id;
    private Long veiculoId;
    private String userCognito;
    private BigDecimal valorVenda;
    private LocalDateTime dataVenda;

    public Venda() {}

    public Venda(Long id, Long veiculoId, String userCognito, BigDecimal valorVenda, LocalDateTime dataVenda) {
        this.id = id;
        this.veiculoId = veiculoId;
        this.userCognito = userCognito;
        this.valorVenda = valorVenda;
        this.dataVenda = dataVenda;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getVeiculoId() { return veiculoId; }
    public void setVeiculoId(Long veiculoId) { this.veiculoId = veiculoId; }
    public String getUserCognito() { return userCognito; }
    public void setUserCognito(String userCognito) { this.userCognito = userCognito; }
    public BigDecimal getValorVenda() { return valorVenda; }
    public void setValorVenda(BigDecimal valorVenda) { this.valorVenda = valorVenda; }
    public LocalDateTime getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDateTime dataVenda) { this.dataVenda = dataVenda; }
}
