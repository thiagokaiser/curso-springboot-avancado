package com.kaiser.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.kaiser.cursomc.services.EmailService;
import com.kaiser.cursomc.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {
	
	@Bean
	public EmailService emailService() {		
		return new SmtpEmailService();
	}

}
