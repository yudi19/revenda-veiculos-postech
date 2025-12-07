package com.example.revenda_veiculos_postech.adapters.out.persistence.mapper;

import com.example.revenda_veiculos_postech.adapters.out.persistence.VendaEntity;
import com.example.revenda_veiculos_postech.domain.model.Venda;

public class VendaEntityMapper {

    public static VendaEntity toEntity(Venda v) {
        VendaEntity e = new VendaEntity();
        e.setId(v.getId());
        e.setVeiculoId(v.getVeiculoId());
        e.setUserCognito(v.getUserCognito());
        e.setValorVenda(v.getValorVenda());
        e.setDataVenda(v.getDataVenda());
        return e;
    }

    public static Venda toDomain(VendaEntity e) {
        return new Venda(
            e.getId(),
            e.getVeiculoId(),
            e.getUserCognito(),
            e.getValorVenda(),
            e.getDataVenda()
        );
    }
}
