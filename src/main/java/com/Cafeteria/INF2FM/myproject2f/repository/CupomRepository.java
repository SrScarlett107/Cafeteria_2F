package com.Cafeteria.INF2FM.myproject2f.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cafeteria.INF2FM.myproject2f.model.Cupom;

import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.Optional;

public interface CupomRepository extends JpaRepository<Cupom, Long> {
    String findByCodigoAndAtivo(String codigo);
}
