package com.forum.forum_hub.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    // Construtor que recebe o AuthenticationManager para realizar a autenticação
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // O método attemptAuthentication é chamado durante a tentativa de login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            // Converte o corpo JSON da requisição para um Map. Este Map vai conter o email e a senha.
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> credentials = objectMapper.readValue(request.getInputStream(), HashMap.class);

            // Recupera as credenciais do Map
            String username = credentials.get("email");  // O nome do campo "email" no corpo da requisição
            String password = credentials.get("senha"); // O nome do campo "senha" no corpo da requisição

            // Cria o token de autenticação para enviar ao AuthenticationManager
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            // Autentica o token com o AuthenticationManager
            return authenticationManager.authenticate(authenticationToken);

        } catch (IOException e) {
            // Se ocorrer um erro ao tentar ler ou processar o JSON, lança uma exceção Runtime
            throw new RuntimeException("Falha ao processar a requisição de autenticação", e);
        }
    }
}
