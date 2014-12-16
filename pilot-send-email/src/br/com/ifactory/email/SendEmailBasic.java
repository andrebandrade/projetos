package br.com.ifactory.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class SendEmailBasic {

	public static void main(String[] args) {
		//SendEmailBasic.sendTextPlan();
		//SendEmailBasic.sendHTML();
		SendEmailBasic.sendHTMLAnexo();
	} // fim de main

	private static void sendTextPlan() {

		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");// o servidor SMTP para o envio do
											// e-mail
		email.setSSLOnConnect(true); // Enviando por uma conexão SSL
		email.setSslSmtpPort("465"); // Porta do servidor de e-mail
		email.setAuthenticator(new DefaultAuthenticator(
				"andre.batista@ifactory.com.br", "88997521AbA"));
		try {
			email.setFrom("andre.batista@ifactory.com.br"); // remetente
			email.addTo("andrbatista@gmail.com", "André Batista"); // destinatário
			email.setSubject("Mensagem de Teste"); // assunto do e-mail
			email.setMsg("Bom dia André, este e-mail é apenas um teste!");
			email.send();
			System.out.println("Email texto plano enviado");
		} catch (EmailException e) {
			e.printStackTrace();
		}
	} // fim do método sendTextPlan

	private static void sendHTML() {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSSLOnConnect(true);
		email.setSslSmtpPort("465");
		email.setAuthenticator(new DefaultAuthenticator("andre.batista@ifactory.com.br", "88997521AbA"));
		try {
			email.setFrom("andre.batista@ifactory.com.br");
			email.addTo("andrbatista@gmail.com");
			
			email.setSubject("Mensagem de Teste - HTML");
			StringBuilder builder = new StringBuilder();
			builder.append("<h2>Titulo</h2>");
			builder.append("<p>Lorem ipsum dolor sit amet, <b>consectetur adipiscing elit</b>. Duis nec aliquam tortor. Sed dignissim dolor ac est consequat egestas. Praesent adipiscing dolor in consectetur fringilla.</p>");
			email.setHtmlMsg(builder.toString());
			email.send();
			System.out.println("E-mail formato html enviado!");
		} catch (EmailException e) {
			e.printStackTrace();
		}
	} // fim do metodo sendHTML
	
	private static void sendHTMLAnexo() {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSSLOnConnect(true);
		email.setSslSmtpPort("465");
		email.setAuthenticator(new DefaultAuthenticator("andre.batista@ifactory.com.br", "88997521AbA"));
		try {
			email.setFrom("andre.batista@ifactory.com.br");
			email.addTo("andrbatista@gmail.com");
			
			email.setSubject("Mensagem de Teste - HTML - Anexo");
			EmailAttachment anexo = new EmailAttachment();
			anexo.setDisposition(EmailAttachment.ATTACHMENT);
			anexo.setPath("./files/anexo.pdf");
			anexo.setName("anexo.pdf");
			email.attach(anexo);
			StringBuilder builder = new StringBuilder();
			builder.append("<h2>Titulo</h2>");
			builder.append("<p>Lorem ipsum dolor sit amet, <b>consectetur adipiscing elit</b>. Duis nec aliquam tortor. Sed dignissim dolor ac est consequat egestas. Praesent adipiscing dolor in consectetur fringilla.</p>");
			email.setHtmlMsg(builder.toString());
			email.send();
			System.out.println("E-mail com anexo enviado!");
		} catch (EmailException e) {
			e.printStackTrace();
		}
	} // fim do metodo sendHTMLAnexo
	
} // fim da classe SendEmailBasic
