package com.example.revenda_veiculos_postech.application.usecase;

import java.time.LocalDateTime;

import com.example.revenda_veiculos_postech.domain.model.Veiculo;
import com.example.revenda_veiculos_postech.domain.model.Venda;
import com.example.revenda_veiculos_postech.domain.port.VeiculoRepositoryPort;
import com.example.revenda_veiculos_postech.domain.port.VendaRepositoryPort;

public class RealizarVendaUseCase {
    private final VendaRepositoryPort vendaRepo;
    private final VeiculoRepositoryPort veiculoRepo;

    public RealizarVendaUseCase(VendaRepositoryPort vendaRepo, VeiculoRepositoryPort veiculoRepo) {
        this.vendaRepo = vendaRepo;
        this.veiculoRepo = veiculoRepo;
    }

    public Venda executar(Long veiculoId, String userCognito) {
        Veiculo veiculo = veiculoRepo.findById(veiculoId)
            .orElseThrow(() -> new IllegalArgumentException("Veiculo não encontrado: " + veiculoId));

        Venda venda = new Venda();
        venda.setVeiculoId(veiculoId);
        venda.setUserCognito(userCognito);
        venda.setValorVenda(veiculo.getPreco());
        venda.setDataVenda(LocalDateTime.now());

        return vendaRepo.save(venda);
    }
}
