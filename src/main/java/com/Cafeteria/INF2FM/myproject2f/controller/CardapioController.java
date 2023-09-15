package com.Cafeteria.INF2FM.myproject2f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coffeteria/cardapio")
public class CardapioController {
	
	@GetMapping("/novo-cardapio")
	public String novoCardapio() {
		
		return "Formulário";
	}

}
