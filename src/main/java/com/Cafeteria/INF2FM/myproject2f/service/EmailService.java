package com.Cafeteria.INF2FM.myproject2f.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.Cafeteria.INF2FM.myproject2f.model.Pagamento;
import com.Cafeteria.INF2FM.myproject2f.model.Pedido;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void enviarEmailComPagina(String destinatario, Pedido pedido) throws MessagingException {
        // Criação da mensagem de email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(destinatario);
        helper.setSubject("Sua Nota Fiscal");

        // Criação do contexto do Thymeleaf
        Context context = new Context();
        context.setVariable("pedido", pedido);

        // Processar o template para gerar o conteúdo HTML
        String htmlContent = templateEngine.process("notaFiscal", context);

        // Configurar o corpo do email como HTML
        helper.setText(htmlContent, true);

        // Enviar o email
        mailSender.send(message);
    }
    
}
