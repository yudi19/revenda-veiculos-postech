package com.example.revenda_veiculos_postech.domain.port;

import java.util.List;
import java.util.Optional;

import com.example.revenda_veiculos_postech.domain.model.Veiculo;

public interface VeiculoRepositoryPort {
    Veiculo save(Veiculo veiculo);
    Optional<Veiculo> findById(Long id);
    List<Veiculo> findAllOrderByPrecoAsc();
}
