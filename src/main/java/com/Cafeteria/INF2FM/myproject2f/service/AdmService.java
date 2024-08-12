package com.Cafeteria.INF2FM.myproject2f.service;

import org.springframework.stereotype.Service;

import com.Cafeteria.INF2FM.myproject2f.repository.AdmRepository;
@Service
public class AdmService {
	private AdmRepository admRepository;
	
	public AdmService(AdmRepository admRepository) {
		this.admRepository = admRepository;
	}
	

}
