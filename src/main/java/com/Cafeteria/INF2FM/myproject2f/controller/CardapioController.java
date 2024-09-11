package com.Cafeteria.INF2FM.myproject2f.controller;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Cafeteria.INF2FM.myproject2f.model.Adm;
import com.Cafeteria.INF2FM.myproject2f.model.Cardapio;
import com.Cafeteria.INF2FM.myproject2f.model.Cupom;
import com.Cafeteria.INF2FM.myproject2f.model.Pedido;
import com.Cafeteria.INF2FM.myproject2f.repository.AdmRepository;
import com.Cafeteria.INF2FM.myproject2f.repository.CardapioRepository;
import com.Cafeteria.INF2FM.myproject2f.repository.PedidoRepository;
import com.Cafeteria.INF2FM.myproject2f.service.CardapioService;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/coffeteria/cardapio")
public class CardapioController {
	@Autowired
	private CardapioRepository cardapioRepository;

	private CardapioService cardapioService;
		
	public CardapioController(CardapioService cardapioService) {
		super();
		this.cardapioService = cardapioService;
	}
	@Autowired
    private PedidoRepository pedidoRepository;



	private String foto = "";
	
	@GetMapping("/inicio")
	public String inicio(Model model) {
		return "Index";
	}

	@GetMapping("/todos-cardapios")
	public String todos(Model model) {
		model.addAttribute("Cardapios", cardapioRepository.findAll());

		return "Cardapios";
	}

	@GetMapping("/novo-cardapio")
	public String novoCardapio(Model model, Cardapio cardapio) {

		model.addAttribute("cardapio", cardapio);
		return "Formulário";
	}

	// add cardapio no banco de dados
	@PostMapping("/add-card")
	String addCardapio(Model model, Cardapio cardapio, BindingResult result) {
		if (result.hasErrors()) {
			return "Formulário";
		}
		cardapio.setCodStatusCardapio(true);

		Cardapio cardapioDb = cardapioRepository.save(cardapio);

		return "redirect:/coffeteria/cardapio/sucesso-cardapio";
	}
	
	@PostMapping("/salvar")
	public String salvar(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@ModelAttribute("cardapio") Cardapio cardapio, BindingResult result) {

		if (result.hasErrors()) {
			return "Formulário";
		}
		cardapio.setCodStatusCardapio(true);
		try {
			cardapio.setFoto(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		cardapioRepository.save(cardapio);

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
	public String todosUsuario(Model model) {
		List<Cardapio> cardapios = cardapioService.findAll();
		model.addAttribute("Cardapios", cardapios);
		
		System.out.println(cardapios.get(0).getFoto());
		return "cardapio";
	}

	@PostMapping("/update/{id}")
	public String atualizarCard(@RequestParam(value = "file", required = false) MultipartFile file,
			@PathVariable("id") Long id, @ModelAttribute("cardapio") Cardapio cardapio, BindingResult result) {

		if (result.hasErrors()) {
			cardapio.setId(id);
			return "editar-card";
		}
byte[] _foto = Base64.getDecoder().decode(foto);
		
		
		foto = "";

		cardapioRepository.save(cardapio);
		return "redirect:/coffeteria/cardapio/todos-cardapios";
	}

	@PostMapping
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}

	// abrir pagina de sucesso do cadastro
	@GetMapping("/sucesso-cardapio")
	String showPageSucessCardapio() {

		return "pagina-sucesso";
	}
	
	@GetMapping("/showImage/{id}")
	@ResponseBody
	public void showImage(@PathVariable("id") Long id, HttpServletResponse response, Cardapio cardapio)
			throws ServletException, IOException {

		cardapio = cardapioService.findById(id);

	
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		if (cardapio.getFoto() != null) {
			response.getOutputStream().write(cardapio.getFoto());
		} else {
			response.getOutputStream().write(null);
		}
		response.getOutputStream().close();
	}
	@PostMapping("/adicionar-pedido")
    public String adicionarPedido(@RequestParam Long cardapioId, @RequestParam Integer quantidade, @RequestParam Double valor, @RequestParam String nomePedido) {
        Pedido pedido = new Pedido();
        pedido.setId_cardapio(cardapioId);
        pedido.setQuantidade(quantidade);
        pedido.setValor(valor);
		pedido.setNomePedido(nomePedido);
        pedidoRepository.save(pedido);
        return "redirect:/coffeteria/cardapio/todos-pedidos";
    }
	@GetMapping("/todos-pedidos")
	public String todosPedidos(Model model, RedirectAttributes redirectAttributes, HttpSession session) {
		model.addAttribute("Pedidos", pedidoRepository.findAll());
		List<Pedido> pedidos = pedidoRepository.findAll();
		double totalValor = pedidos.stream().mapToDouble(Pedido::getValor).sum();  // Calcula a soma dos valores

		model.addAttribute("Pedidos", pedidos);
		model.addAttribute("totalValor", totalValor);  // Passa o total para o modelo
	
		 // Adiciona o valor para redirecionamento
		 redirectAttributes.addFlashAttribute("totalValor", totalValor);
		 session.setAttribute("totalValor", totalValor); 

		return "Pedidos";
	}
	@GetMapping("/pagamento")
	public String pagamento(Model model, HttpSession session) {
		Double totalValor = (Double) session.getAttribute("totalValor");
        model.addAttribute("totalValor", totalValor);

		
		return "pagamento";
	}
	@GetMapping("/cartao")
	public String cartao(Model model) {

		return "confirmacaopagmt";

	}
	@GetMapping("/pix")
	public String pix(Model model) {
		return "pix";
	}
	@GetMapping("/dinheiro")
	public String dinheiro(Model model) {
		return "dinheiro";
	}
	@GetMapping("/finalizar")
	public String finalizar(Model model) {

		return "conclusaocompra";
		

	}
	@GetMapping("/Login")
	public String login1(Model model) {
		
		return "LoginAdm";
		
	}
	@PostMapping("/resetar-pedido")
    public String resetarPedido() {
		pedidoRepository.deleteAll();

        
        return "redirect:/coffeteria/cardapio/inicio";
    }
	Adm adm;
	
	AdmRepository admRepository;
	
	@PostMapping("/logar")
	public String logar(Model model, Adm adm) {
		model.addAttribute("Adms", admRepository.findBy(null, null ) );
		return "";
		
		
	}
	



	

}
