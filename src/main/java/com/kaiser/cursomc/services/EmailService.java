package com.kaiser.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.kaiser.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
