package com.example.revenda_veiculos_postech.domain.port;

import java.util.List;
import java.util.Optional;

import com.example.revenda_veiculos_postech.domain.model.Venda;

public interface VendaRepositoryPort {
    Venda save(Venda venda);
    Optional<Venda> findById(Long id);
    List<Venda> findAllOrderByValorVendaAsc();
}
