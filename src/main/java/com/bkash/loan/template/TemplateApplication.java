package com.bkash.loan.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TemplateApplication{


	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}
}
