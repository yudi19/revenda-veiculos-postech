package com.example.revenda_veiculos_postech.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.revenda_veiculos_postech.application.usecase.CadastrarVeiculoUseCase;
import com.example.revenda_veiculos_postech.application.usecase.EditarVeiculoUseCase;
import com.example.revenda_veiculos_postech.application.usecase.ListarVeiculosUseCase;
import com.example.revenda_veiculos_postech.application.usecase.ListarVendasUseCase;
import com.example.revenda_veiculos_postech.application.usecase.RealizarVendaUseCase;
import com.example.revenda_veiculos_postech.domain.port.VeiculoRepositoryPort;
import com.example.revenda_veiculos_postech.domain.port.VendaRepositoryPort;

@Configuration
public class UseCaseConfig {

    @Bean
    public CadastrarVeiculoUseCase cadastrarVeiculoUseCase(VeiculoRepositoryPort repo) {
        return new CadastrarVeiculoUseCase(repo);
    }

    @Bean
    public EditarVeiculoUseCase editarVeiculoUseCase(VeiculoRepositoryPort repo) {
        return new EditarVeiculoUseCase(repo);
    }

    @Bean
    public ListarVeiculosUseCase listarVeiculosUseCase(VeiculoRepositoryPort repo) {
        return new ListarVeiculosUseCase(repo);
    }

    @Bean
    public RealizarVendaUseCase realizarVendaUseCase(VendaRepositoryPort vendaRepo, VeiculoRepositoryPort veiculoRepo) {
        return new RealizarVendaUseCase(vendaRepo, veiculoRepo);
    }

    @Bean
    public ListarVendasUseCase listarVendasUseCase(VendaRepositoryPort vendaRepo) {
        return new ListarVendasUseCase(vendaRepo);
    }
}
