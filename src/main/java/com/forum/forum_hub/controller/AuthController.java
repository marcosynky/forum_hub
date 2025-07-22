package com.forum.forum_hub.controller;

import com.forum.forum_hub.models.JwtResponse;
import com.forum.forum_hub.models.LoginRequest;
import com.forum.forum_hub.service.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;  // Para autenticar o usuário com as credenciais

    @Autowired
    private JwtTokenProvider jwtTokenProvider;  // Para gerar o JWT após autenticação bem-sucedida

    @Autowired
    private PasswordEncoder passwordEncoder;  // Para codificar as senhas (se necessário)

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Aqui usamos o AuthenticationManager para autenticar o usuário com o e-mail e senha fornecidos
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha())
            );

            // Se a autenticação for bem-sucedida, a autenticação é registrada no contexto de segurança
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Gera o token JWT para o usuário autenticado
            String token = jwtTokenProvider.generateToken(authentication);

            // Retorna o token no corpo da resposta
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (BadCredentialsException e) {
            // Caso as credenciais sejam inválidas, retorna o erro 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}
