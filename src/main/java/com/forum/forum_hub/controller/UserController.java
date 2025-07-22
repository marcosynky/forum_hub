package com.forum.forum_hub.controller;


import com.forum.forum_hub.UserCreateDTO;
import com.forum.forum_hub.UserRepository;
import com.forum.forum_hub.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;  // Repositório para a entidade User

    @Autowired
    private PasswordEncoder passwordEncoder; // Usando o PasswordEncoder para criptografar a senha

    // Endpoint para criar um novo usuário
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        // Verifica se o e-mail já está registrado
        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Usuário já existe com esse e-mail.");
        }

        // Criação do novo usuário
        User user = new User();
        user.setEmail(userCreateDTO.getEmail());
        user.setSenha(passwordEncoder.encode(userCreateDTO.getSenha())); // Criptografando a senha
        userRepository.save(user);

        return ResponseEntity.ok("Usuário criado com sucesso.");
    }
}
