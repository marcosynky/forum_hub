package com.forum.forum_hub; // Declara o pacote onde a classe está localizada

import org.springframework.boot.SpringApplication; // Importa a classe SpringApplication, que ajuda a inicializar a aplicação Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa a anotação SpringBootApplication, que configura automaticamente a aplicação Spring Boot

// A anotação @SpringBootApplication é um atalho para configurar várias anotações do Spring em um único lugar.
// Ela inclui @Configuration (configuração da aplicação), @EnableAutoConfiguration (habilita configurações automáticas),
// e @ComponentScan (permite que o Spring encontre e registre componentes, serviços e outros beans automaticamente).
@SpringBootApplication
public class ForumHubApplication { // Declaração da classe principal da aplicação Spring Boot

	// O método main é o ponto de entrada da aplicação Java
	public static void main(String[] args) {
		// A linha abaixo inicia a aplicação Spring Boot
		SpringApplication.run(ForumHubApplication.class, args);
		// O método 'run' inicia o contexto do Spring e a aplicação, fazendo com que o servidor seja executado
	}
}
