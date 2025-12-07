package com.example.revenda_veiculos_postech.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.revenda_veiculos_postech.adapters.out.persistence.mapper.VeiculoEntityMapper;
import com.example.revenda_veiculos_postech.domain.model.Veiculo;
import com.example.revenda_veiculos_postech.domain.port.VeiculoRepositoryPort;

@Component
public class VeiculoRepositoryAdapter implements VeiculoRepositoryPort {

    private final VeiculoJpaRepository jpaRepository;

    public VeiculoRepositoryAdapter(VeiculoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Veiculo save(Veiculo veiculo) {
        VeiculoEntity entity = VeiculoEntityMapper.toEntity(veiculo);
        VeiculoEntity saved = jpaRepository.save(entity);
        return VeiculoEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Veiculo> findById(Long id) {
        return jpaRepository.findById(id).map(VeiculoEntityMapper::toDomain);
    }

    @Override
    public List<Veiculo> findAllOrderByPrecoAsc() {
        return jpaRepository.findAllByOrderByPrecoAsc()
                .stream()
                .map(VeiculoEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
