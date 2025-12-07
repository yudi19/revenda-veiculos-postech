package com.example.revenda_veiculos_postech.application.dto;

import java.math.BigDecimal;

import com.example.revenda_veiculos_postech.domain.model.Veiculo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VeiculoRequest {
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotNull @Min(1886)
    private Integer ano;
    @NotBlank
    private String cor;
    @NotNull @Positive
    private BigDecimal preco;

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public Veiculo toDomain() {
        return new Veiculo(null, marca, modelo, ano, cor, preco);
    }
}
