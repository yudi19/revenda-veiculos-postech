package com.example.revenda_veiculos_postech.domain.model;

import java.math.BigDecimal;

public class Veiculo {
    private Long id;
    private String marca;
    private String modelo;
    private Integer ano;
    private String cor;
    private BigDecimal preco;

    public Veiculo() {}

    public Veiculo(Long id, String marca, String modelo, Integer ano, String cor, BigDecimal preco) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
}
