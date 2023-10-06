package com.Cafeteria.INF2FM.myproject2f.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Cafeteria.INF2FM.myproject2f.model.Cardapio;
import com.Cafeteria.INF2FM.myproject2f.repository.CardapioRepository;

@Controller
@RequestMapping("/coffeteria/cardapio")
public class CardapioController {
	@Autowired
	private CardapioRepository cardapioRepository;
	
	public List<Cardapio> listar(){
		return cardapioRepository.findAll();
	}
	@GetMapping("/novo-cardapio")
	public String novoCardapio(Model model, Cardapio cardapio) {
		
		model.addAttribute("cardapio", cardapio);
		return "Formulário";
	}
	
	//add cardapio no banco de dados
	@PostMapping("/add-card")
	String addCardapio(Model model, Cardapio cardapio) {
		cardapio.setCodStatusCardapio(true);
		
		Cardapio cardapioDb = cardapioRepository.save(cardapio);
		
	
		return "redirect:/coffeteria/cardapio/sucesso-cardapio";
	}
	
	//abrir pagina de sucesso do cadastro
	@GetMapping("/sucesso-cardapio")
	String showPageSucessCardapio() {
		
		return "pagina-sucesso";
	}
	
	@PostMapping
	public void incluir(@RequestBody Cardapio cardapio) {
		
	}
		
	

}
