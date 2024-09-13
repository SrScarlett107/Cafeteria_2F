package com.Cafeteria.INF2FM.myproject2f.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cafeteria.INF2FM.myproject2f.model.Cupom;



@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {
    
    Cupom findByCodigo(String codigo);
}
