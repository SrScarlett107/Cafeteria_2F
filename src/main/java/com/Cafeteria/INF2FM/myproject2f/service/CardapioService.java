package com.Cafeteria.INF2FM.myproject2f.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Cafeteria.INF2FM.myproject2f.model.Cardapio;
import com.Cafeteria.INF2FM.myproject2f.repository.CardapioRepository;

@Service
public class CardapioService {
		private CardapioRepository cardapioRepository;

		public CardapioService(CardapioRepository cardapioRepository) {
			this.cardapioRepository = cardapioRepository;
		}
	
		public List<Cardapio> findAll() {
			List<Cardapio> cardapios = cardapioRepository.findAll();
			return cardapios;
		}

		public Cardapio findById(Long id) {
			return cardapioRepository.findById(id).get();
		}

		
}