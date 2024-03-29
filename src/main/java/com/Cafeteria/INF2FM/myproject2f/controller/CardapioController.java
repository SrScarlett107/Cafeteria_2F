package com.Cafeteria.INF2FM.myproject2f.controller;


import java.io.IOException;
import java.util.Base64;

//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.EnumSet;
//import java.util.List;
//import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Cafeteria.INF2FM.myproject2f.model.Cardapio;
import com.Cafeteria.INF2FM.myproject2f.repository.CardapioRepository;

@Controller
@RequestMapping("/coffeteria/cardapio")
public class CardapioController {
	@Autowired
	private CardapioRepository cardapioRepository;
	
private String foto = "";

@GetMapping("/todos-cardapios")
public String todos(Model model){
	model.addAttribute("Cardapios", cardapioRepository.findAll());

	
	return "Cardapios";
}

	@GetMapping("/novo-cardapio")
	public String novoCardapio(Model model, Cardapio cardapio) {
		
		model.addAttribute("cardapio", cardapio);
		return "Formulário";
	}
	
	//add cardapio no banco de dados
	@PostMapping("/add-card")
	String addCardapio(Model model, Cardapio cardapio, BindingResult result) {
		if (result.hasErrors()){
			return "Formulário";
		}
		cardapio.setCodStatusCardapio(true);
		
		Cardapio cardapioDb = cardapioRepository.save(cardapio);
		
	
		return "redirect:/coffeteria/cardapio/sucesso-cardapio";
	}
	
	@GetMapping("/editar-card/{id}")
	public String showUpdateForm(@PathVariable("id") long id, ModelMap model) {
		Cardapio cardapio = cardapioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid card Id:" + id));

				if (cardapio.getFoto() != null) {
					foto = Base64.getEncoder().encodeToString(cardapio.getFoto());
				}

		model.addAttribute("cardapio", cardapio);
		return "editar-card";
	}

	@GetMapping("/usuario-cardapio")
	public String showCardapio(Model model, Cardapio cardapio) {
		return "cardapio";
	}
	
	
	

@SuppressWarnings("null")
@PostMapping("/update/{id}")
	public String atualizarCard(
			@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") Long id, 
			@ModelAttribute("cardapio") Cardapio cardapio, BindingResult result) {

		if (result.hasErrors()) {
			cardapio.setId(id);
			return "editar-card";
		}
		if (file != null && !file.getOriginalFilename().isEmpty()) {
			try {
				cardapio.setFoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			cardapio.setFoto(null);
		}
	
		cardapioRepository.save(cardapio);
		return "redirect:/coffeteria/cardapio/todos-cardapios";
	}

	@PostMapping
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}

	


	//abrir pagina de sucesso do cadastro
	@GetMapping("/sucesso-cardapio")
	String showPageSucessCardapio() {
		
		return "pagina-sucesso";
	}
	
		
	

}
