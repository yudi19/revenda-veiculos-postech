package com.example.revenda_veiculos_postech.adapters.out.persistence.mapper;

import com.example.revenda_veiculos_postech.adapters.out.persistence.VeiculoEntity;
import com.example.revenda_veiculos_postech.domain.model.Veiculo;

public class VeiculoEntityMapper {

    public static VeiculoEntity toEntity(Veiculo v) {
        VeiculoEntity e = new VeiculoEntity();
        e.setId(v.getId());
        e.setMarca(v.getMarca());
        e.setModelo(v.getModelo());
        e.setAno(v.getAno());
        e.setCor(v.getCor());
        e.setPreco(v.getPreco());
        return e;
    }

    public static Veiculo toDomain(VeiculoEntity e) {
        return new Veiculo(
            e.getId(),
            e.getMarca(),
            e.getModelo(),
            e.getAno(),
            e.getCor(),
            e.getPreco()
        );
    }
}
