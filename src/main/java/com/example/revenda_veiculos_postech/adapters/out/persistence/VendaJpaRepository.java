package com.example.revenda_veiculos_postech.adapters.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaJpaRepository extends JpaRepository<VendaEntity, Long> {
    List<VendaEntity> findAllByOrderByValorVendaAsc();
}
