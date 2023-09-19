package com.Cafeteria.INF2FM.myproject2f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Cafeteria.INF2FM.myproject2f.model.Cardapio;

@Controller
@RequestMapping("/coffeteria/cardapio")
public class CardapioController {
	
	@GetMapping("/novo-cardapio")
	public String novoCardapio(Model model, Cardapio cardapio) {
		
		model.addAttribute("cardapio", cardapio);
		return "Formulário";
	}
	
	String addCardapio(Model model, Cardapio cardapio) {
		//trabalhando com datas
		
		String pattern = "yyyy-MM-dd";
		return null;
	}

}
