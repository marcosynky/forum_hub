package com.forum.forum_hub.controller;

import com.forum.forum_hub.UserRepository;
import com.forum.forum_hub.models.JwtResponse;
import com.forum.forum_hub.models.LoginRequest;
import com.forum.forum_hub.models.User;
import com.forum.forum_hub.service.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserRepository userRepository;  // Repositório para interagir com o banco de dados

    @Autowired
    private PasswordEncoder passwordEncoder;  // Responsável pela criptografia da senha

    @Autowired
    private JwtTokenProvider jwtTokenProvider;  // Para geração do JWT token

    // Endpoint de registro de usuário
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
        }

        String email = loginRequest.getEmail().trim();
        String senha = loginRequest.getSenha().trim();

        // Verifica se o usuário já está registrado
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este email já está registrado.");
        }

        // Se não estiver registrado, cria um novo usuário
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setSenha(passwordEncoder.encode(senha));  // Criptografa a senha
        userRepository.save(newUser);

        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
        }

        String email = loginRequest.getEmail().trim();
        String senha = loginRequest.getSenha().trim();

        // Verifica se o usuário existe no banco de dados
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Compara a senha fornecida com a senha criptografada no banco de dados
            if (passwordEncoder.matches(senha, user.getSenha())) {
                // Gera o token JWT
                String token = jwtTokenProvider.generateToken((Authentication) user);
                return ResponseEntity.ok(new JwtResponse(token));  // Retorna o token JWT
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
    }
}
