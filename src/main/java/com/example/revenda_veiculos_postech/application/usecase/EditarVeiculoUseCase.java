package com.example.revenda_veiculos_postech.application.usecase;

import com.example.revenda_veiculos_postech.domain.model.Veiculo;
import com.example.revenda_veiculos_postech.domain.port.VeiculoRepositoryPort;

public class EditarVeiculoUseCase {
    private final VeiculoRepositoryPort repo;

    public EditarVeiculoUseCase(VeiculoRepositoryPort repo) {
        this.repo = repo;
    }

    public Veiculo executar(Long id, Veiculo dados) {
        Veiculo existente = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado: " + id));
        existente.setMarca(dados.getMarca());
        existente.setModelo(dados.getModelo());
        existente.setAno(dados.getAno());
        existente.setCor(dados.getCor());
        existente.setPreco(dados.getPreco());
        return repo.save(existente);
    }
}
