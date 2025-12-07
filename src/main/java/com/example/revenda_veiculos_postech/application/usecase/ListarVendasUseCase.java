package com.example.revenda_veiculos_postech.application.usecase;

import java.util.List;

import com.example.revenda_veiculos_postech.domain.model.Venda;
import com.example.revenda_veiculos_postech.domain.port.VendaRepositoryPort;

public class ListarVendasUseCase {
    private final VendaRepositoryPort vendaRepo;

    public ListarVendasUseCase(VendaRepositoryPort vendaRepo) {
        this.vendaRepo = vendaRepo;
    }

    public List<Venda> executar() {
        return vendaRepo.findAllOrderByValorVendaAsc();
    }
}
