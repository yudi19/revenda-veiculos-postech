package com.example.revenda_veiculos_postech.application.usecase;

import com.example.revenda_veiculos_postech.domain.model.Veiculo;
import com.example.revenda_veiculos_postech.domain.port.VeiculoRepositoryPort;

public class CadastrarVeiculoUseCase {
    private final VeiculoRepositoryPort repo;

    public CadastrarVeiculoUseCase(VeiculoRepositoryPort repo) {
        this.repo = repo;
    }

    public Veiculo executar(Veiculo veiculo) {
        veiculo.setId(null);
        return repo.save(veiculo);
    }
}
