package com.example.revenda_veiculos_postech.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.revenda_veiculos_postech.adapters.out.persistence.mapper.VendaEntityMapper;
import com.example.revenda_veiculos_postech.domain.model.Venda;
import com.example.revenda_veiculos_postech.domain.port.VendaRepositoryPort;

@Component
public class VendaRepositoryAdapter implements VendaRepositoryPort {

    private final VendaJpaRepository jpaRepository;

    public VendaRepositoryAdapter(VendaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Venda save(Venda venda) {
        VendaEntity entity = VendaEntityMapper.toEntity(venda);
        VendaEntity saved = jpaRepository.save(entity);
        return VendaEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Venda> findById(Long id) {
        return jpaRepository.findById(id).map(VendaEntityMapper::toDomain);
    }

    @Override
    public List<Venda> findAllOrderByValorVendaAsc() {
        return jpaRepository.findAllByOrderByValorVendaAsc()
                .stream()
                .map(VendaEntityMapper::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }
}
