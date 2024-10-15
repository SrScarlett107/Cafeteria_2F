package com.Cafeteria.INF2FM.myproject2f.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import org.thymeleaf.TemplateEngine;

import com.Cafeteria.INF2FM.myproject2f.model.Adm;
import com.Cafeteria.INF2FM.myproject2f.model.Cardapio;
import com.Cafeteria.INF2FM.myproject2f.model.Cupom;
import com.Cafeteria.INF2FM.myproject2f.model.Pagamento;
import com.Cafeteria.INF2FM.myproject2f.model.Pedido;
import com.Cafeteria.INF2FM.myproject2f.repository.AdmRepository;
import com.Cafeteria.INF2FM.myproject2f.repository.CardapioRepository;
import com.Cafeteria.INF2FM.myproject2f.repository.CupomRepository;
import com.Cafeteria.INF2FM.myproject2f.repository.PedidoRepository;
import com.Cafeteria.INF2FM.myproject2f.service.CardapioService;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



@Controller
@RequestMapping("/coffeteria/cardapio")
public class CardapioController {
	@Autowired
	private CardapioRepository cardapioRepository;

	private CardapioService cardapioService;

	@Autowired
	AdmRepository admRepository;

	@Autowired
	CupomRepository cupomRepository;

		
	public CardapioController(CardapioService cardapioService) {
		super();
		this.cardapioService = cardapioService;
	}
	@Autowired
    private PedidoRepository pedidoRepository;



	private String foto = "";
	
	private double totalValor = 0;
	
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
public String atualizarCard(
        @RequestParam(value = "file", required = false) MultipartFile file, // Arquivo recebido como MultipartFile
        @PathVariable("id") Long id, 
        @ModelAttribute("cardapio") Cardapio cardapio, 
        BindingResult result) {

    // Verifique se há erros de validação
    if (result.hasErrors()) {
        cardapio.setId(id);
        return "editar-card";
    }

    // Se o arquivo 'foto' não estiver vazio, faça o upload
    if (!foto.isEmpty()) {
        try {
            // Converte MultipartFile para byte[]
            cardapio.setFoto(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace(); // Log do erro
        }
    } else {
        // Caso não haja uma nova imagem, mantenha a imagem existente
        Cardapio existingCardapio = cardapioService.findById(id);
        cardapio.setFoto(existingCardapio.getFoto());
    }

    // Salve o cardápio no banco de dados
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
	public String todosPedidos(Model model, RedirectAttributes redirectAttributes, HttpSession session, Pedido pedido) {
		model.addAttribute("Pedidos", pedidoRepository.findAll());
		List<Pedido> pedidos = pedidoRepository.findAll();
		totalValor = pedidos.stream().mapToDouble(Pedido::getValor).sum();  // Calcula a soma dos valores

		model.addAttribute("Pedidos", pedidos);
		model.addAttribute("totalValor", totalValor);  // Passa o total para o modelo
	
		 // Adiciona o valor para redirecionamento
		 redirectAttributes.addFlashAttribute("totalValor", totalValor);
		 session.setAttribute("totalValor", totalValor); 


		return "Pedidos";
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
	public String login1(Model model, Adm adm) {

		model.addAttribute("adm", adm);
		
		return "LoginAdm";
		
	}
	@PostMapping("/resetar-pedido")
    public String resetarPedido() {
		pedidoRepository.deleteAll();

        
        return "redirect:/coffeteria/cardapio/inicio";
    }

	@GetMapping("/interface")
	public String admInterface(Model model) {
		return "confirmacaoADM";
	}
	

	
	@PostMapping("/logar")
	public String logar(Model model, Adm adm) {
		Adm admdb = admRepository.findByUsuario(adm.getUsuario());
		if(admdb != null && adm.getSenha() == (admdb.getSenha())) {
			return "redirect:/coffeteria/cardapio/interface";
			
			}
			
		else {
			return "redirect:/coffeteria/cardapio/Login";
		}	
	}
	
	@GetMapping("/pagamento")
	public String pagamento(Model model, HttpSession session, Cupom cupom) {
		
		session.setAttribute("totalValor", totalValor);
        model.addAttribute("totalValor", session.getAttribute("totalValor"));
		model.addAttribute("cupom", cupom);

		
		return "pagamento";
	}

	@PostMapping("/verificarCupom")
	public String cupom(Model model, Cupom cupom, HttpSession session) {
		Cupom cupomdb = cupomRepository.findByCodigo(cupom.getCodigo());
		model.addAttribute("cupom", cupom);

		if (cupomdb != null) {
		
			totalValor = totalValor - (totalValor*0.10);
			session.setAttribute("totalValor", totalValor);
			model.addAttribute("totalValor", session.getAttribute("totalValor"));
	
			return "redirect:/coffeteria/cardapio/pagamento";
		}
		else{
			return "redirect:/coffeteria/cardapio/pagamento";
		}
		

		
		
	}

	@GetMapping("/notaFiscal")
	public String nota(Model model, HttpSession session, Pedido pedido, Pagamento pagamento ) {
		model.addAttribute("totalValor", session.getAttribute("totalValor"));
		session.setAttribute("totalValor", totalValor);

		List<Pedido> pedidos = pedidoRepository.findAll();
		model.addAttribute("Pedidos", pedidos);
		model.addAttribute("pagamento", pagamento);
		
		return "notaFiscal";
	}
	@Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("/enviar-email")
    public String enviarEmail(String emailDestinatario, Model model, HttpSession session, Pedido pedido, Pagamento pagamento) throws MessagingException {
        // Criar o contexto e adicionar dados
		Object totalValor = session.getAttribute("totalValor");
	
		List<Pedido> pedidos = pedidoRepository.findAll();

        Context context = new Context();
        context.setVariable("data", "08 de Outubro de 2024");

		
		context.setVariable("data", "16 de Outubro de 2024");
        context.setVariable("totalValor", totalValor);
        context.setVariable("pagamento", pagamento);
        context.setVariable("pedidos", pedidos);


        // Processar o template para HTML
        String html = templateEngine.process("notaFiscal", context);
		emailDestinatario = "edu25070@gmail.com";

        // Criar e enviar o email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(emailDestinatario);
        helper.setSubject("Nota Fiscal Gerada");
        helper.setText(html, true);  // o segundo argumento é para indicar que é HTML

        mailSender.send(message);

        return "redirect:/coffeteria/cardapio/inicio"; // Retornar uma view de confirmação, se necessário
    }




	@GetMapping("/deletar/{id}")
    public String deletarPedido(@PathVariable("id") Long id) {
    pedidoRepository.deleteById(id);
        return "redirect:/coffeteria/cardapio/todos-pedidos";
    }
	@GetMapping("/deletarItem/{id}")
    public String deletarItem(@PathVariable("id") Long id) {
    cardapioRepository.deleteById(id);
        return "redirect:/coffeteria/cardapio/todos-cardapios"; 
    }

	
	



	

}
