package com.example.revenda_veiculos_postech.application.usecase;

import java.util.List;

import com.example.revenda_veiculos_postech.domain.model.Veiculo;
import com.example.revenda_veiculos_postech.domain.port.VeiculoRepositoryPort;

public class ListarVeiculosUseCase {
    private final VeiculoRepositoryPort repo;

    public ListarVeiculosUseCase(VeiculoRepositoryPort repo) {
        this.repo = repo;
    }

    public List<Veiculo> executar() {
        return repo.findAllOrderByPrecoAsc();
    }
}
