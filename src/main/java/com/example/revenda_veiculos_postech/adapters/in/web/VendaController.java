package com.example.revenda_veiculos_postech.adapters.in.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.revenda_veiculos_postech.application.dto.VeiculoResponse;
import com.example.revenda_veiculos_postech.application.dto.VendaDetalhadaResponse;
import com.example.revenda_veiculos_postech.application.dto.VendaRequest;
import com.example.revenda_veiculos_postech.application.dto.VendaResponse;
import com.example.revenda_veiculos_postech.application.usecase.ListarVendasUseCase;
import com.example.revenda_veiculos_postech.application.usecase.RealizarVendaUseCase;
import com.example.revenda_veiculos_postech.domain.model.Veiculo;
import com.example.revenda_veiculos_postech.domain.model.Venda;
import com.example.revenda_veiculos_postech.domain.port.VeiculoRepositoryPort;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final RealizarVendaUseCase realizarVendaUseCase;
    private final ListarVendasUseCase listarVendasUseCase;
    private final VeiculoRepositoryPort veiculoRepo;

    public VendaController(RealizarVendaUseCase realizarVendaUseCase, ListarVendasUseCase listarVendasUseCase, VeiculoRepositoryPort veiculoRepo) {
        this.realizarVendaUseCase = realizarVendaUseCase;
        this.listarVendasUseCase = listarVendasUseCase;
        this.veiculoRepo = veiculoRepo;
    }

    @GetMapping
    public ResponseEntity<List<VendaDetalhadaResponse>> listarVendas() {
        List<Venda> vendas = listarVendasUseCase.executar();
        
        List<VendaDetalhadaResponse> response = vendas.stream()
                .map(venda -> {
                    Veiculo veiculo = veiculoRepo.findById(venda.getVeiculoId())
                            .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado"));
                    
                    return new VendaDetalhadaResponse(
                        venda.getId(),
                        venda.getUserCognito(),
                        venda.getValorVenda(),
                        venda.getDataVenda(),
                        VeiculoResponse.fromDomain(veiculo)
                    );
                })
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<VendaResponse> realizarVenda(
        @Valid @RequestBody VendaRequest body,
        @AuthenticationPrincipal String userCognito
    ) {
        Venda venda = realizarVendaUseCase.executar(body.getVeiculoId(), userCognito);
        
        VendaResponse response = new VendaResponse(
            venda.getId(),
            venda.getVeiculoId(),
            venda.getUserCognito(),
            venda.getValorVenda(),
            venda.getDataVenda()
        );
        
        return ResponseEntity.ok(response);
    }
}
