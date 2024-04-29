package com.Cafeteria.INF2FM.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Cafeteria.INF2FM.myproject2f.model.Cardapio;
import com.Cafeteria.INF2FM.myproject2f.repository.CardapioRepository;

@Service
public class CardapioService {
		private CardapioRepository cardapioRepository;

		public CardapioService(CardapioRepository cardapioRepository) {
			super();
			this.cardapioRepository = cardapioRepository;
		}

		public List<Cardapio> findAll() {
			List<Cardapio> cardapios = cardapioRepository.findAll();
			return cardapios;
		}
}