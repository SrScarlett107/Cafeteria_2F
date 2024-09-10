package com.Cafeteria.INF2FM.myproject2f.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cafeteria.INF2FM.myproject2f.model.Cupom;
import com.Cafeteria.INF2FM.myproject2f.repository.CupomRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.Optional;

@Service
public class CupomService {
    Cupom cupom;

    @Autowired
    private CupomRepository cupomRepository;

    public String verificarCupom(String codigo) {
        // Verifica se o cupom existe e está ativo
        return cupomRepository.findByCodigoAndAtivo(codigo);
    }

    public double aplicarDesconto(double totalValor, double descontoPercentual) {
        // Calcula o novo valor com desconto
        return totalValor - (totalValor * (descontoPercentual / 100));
    }
}

