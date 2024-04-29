package com.Cafeteria.INF2FM.myproject2f.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cafeteria.INF2FM.myproject2f.model.Cardapio;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long>{
	List<Cardapio> findAll();

}
