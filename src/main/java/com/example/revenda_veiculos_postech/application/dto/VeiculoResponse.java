package com.example.revenda_veiculos_postech.application.dto;

import java.math.BigDecimal;

import com.example.revenda_veiculos_postech.domain.model.Veiculo;

public class VeiculoResponse {
    private Long id;
    private String marca;
    private String modelo;
    private Integer ano;
    private String cor;
    private BigDecimal preco;

    public VeiculoResponse(Long id, String marca, String modelo, Integer ano, String cor, BigDecimal preco) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public Integer getAno() { return ano; }
    public String getCor() { return cor; }
    public BigDecimal getPreco() { return preco; }

    public static VeiculoResponse fromDomain(Veiculo v) {
        return new VeiculoResponse(v.getId(), v.getMarca(), v.getModelo(), v.getAno(), v.getCor(), v.getPreco());
    }
}
