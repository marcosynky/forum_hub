package com.forum.forum_hub.controller;


import com.forum.forum_hub.models.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (isValidUser(loginRequest)) {
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }

    private boolean isValidUser(LoginRequest loginRequest) {
        String email = loginRequest.getEmail().trim();
        String senha = loginRequest.getSenha().trim();

        // Verifique as credenciais corretamente
        return "marcosynk@gmail.com".equalsIgnoreCase(email) && "samuel1978".equals(senha);
    }
}

