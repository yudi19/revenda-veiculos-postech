package com.example.revenda_veiculos_postech.adapters.in.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.revenda_veiculos_postech.application.dto.VeiculoRequest;
import com.example.revenda_veiculos_postech.application.dto.VeiculoResponse;
import com.example.revenda_veiculos_postech.application.usecase.CadastrarVeiculoUseCase;
import com.example.revenda_veiculos_postech.application.usecase.EditarVeiculoUseCase;
import com.example.revenda_veiculos_postech.application.usecase.ListarVeiculosUseCase;
import com.example.revenda_veiculos_postech.domain.model.Veiculo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final CadastrarVeiculoUseCase cadastrarUseCase;
    private final EditarVeiculoUseCase editarUseCase;
    private final ListarVeiculosUseCase listarUseCase;

    public VeiculoController(CadastrarVeiculoUseCase cadastrarUseCase, EditarVeiculoUseCase editarUseCase, ListarVeiculosUseCase listarUseCase) {
        this.cadastrarUseCase = cadastrarUseCase;
        this.editarUseCase = editarUseCase;
        this.listarUseCase = listarUseCase;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> listar() {
        List<Veiculo> veiculos = listarUseCase.executar();
        List<VeiculoResponse> response = veiculos.stream()
                .map(VeiculoResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<VeiculoResponse> cadastrar(@Valid @RequestBody VeiculoRequest body) {
        Veiculo salvo = cadastrarUseCase.executar(body.toDomain());
        return ResponseEntity.ok(VeiculoResponse.fromDomain(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponse> editar(@PathVariable Long id, @Valid @RequestBody VeiculoRequest body) {
        Veiculo atualizado = editarUseCase.executar(id, body.toDomain());
        return ResponseEntity.ok(VeiculoResponse.fromDomain(atualizado));
    }
}
