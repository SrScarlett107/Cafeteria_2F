package com.Cafeteria.INF2FM.myproject2f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Cafeteria.INF2FM.myproject2f.model.Adm;
import com.Cafeteria.INF2FM.myproject2f.repository.AdmRepository;

@Controller
@RequestMapping("/coffeteria/login")
public class AdmController {
@Autowired
private AdmRepository admRepository;

@GetMapping("/todos-adms")
public String todos(Model model) {
	model.addAttribute("Adms", admRepository.findAll());
	return "adms";
}
@GetMapping("/novo-adm")
public String novoAdm(Model model , Adm adm) {
	model.addAttribute("adm", adm);
	return "novoAdm";
	
}

}
