package com.Cafeteria.INF2FM.myproject2f.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cafeteria.INF2FM.myproject2f.model.Adm;


public interface AdmRepository extends JpaRepository<Adm, Long>{
	String findBy(String usuario, int senha);



}
