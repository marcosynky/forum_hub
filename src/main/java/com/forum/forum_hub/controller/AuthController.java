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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;  // Caso utilize JWT

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) { // Alterado para receber LoginRequest
        try {
            Authentication authentication = authenticationManager.authenticate( // Autenticação
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()) // Autenticação
            );

            SecurityContextHolder.getContext().setAuthentication(authentication); // Autenticação

            // Geração do token JWT após autenticação
            String token = jwtTokenProvider.generateToken(authentication);

            return ResponseEntity.ok(new JwtResponse(token)); // Retorna o token JWT
        } catch (BadCredentialsException e) { // Caso as credenciais sejam inválidas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas"); // Retorna erro 401
        }
    }
}
