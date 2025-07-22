package com.forum.forum_hub.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${frontend.url}")  // A URL do frontend será injetada aqui
    private String frontendUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) { // Configurando o CORS
        registry.addMapping("/**")
                .allowedOrigins(frontendUrl)  // Usando o valor injetado de frontend.url
                .allowedMethods("GET", "POST", "PUT", "DELETE")// Definindo os métodos permitidos
                .allowedHeaders("*")// Definindo os headers permitidos
                .allowCredentials(true);// Habilitando credenciais
    }
}
