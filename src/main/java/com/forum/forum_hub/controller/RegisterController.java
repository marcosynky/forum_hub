package com.forum.forum_hub.controller;

import com.forum.forum_hub.models.User;
import com.forum.forum_hub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    // O método agora usa @Valid para garantir que a validação seja feita antes de continuar.
    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody User usuario, BindingResult bindingResult) {
        // Verifica se há erros de validação no corpo da requisição
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos, por favor verifique os campos.");
        }

        try {
            // Chama o método de criação do usuário no UserService
            userService.criarUsuario(usuario.getEmail(), usuario.getNome(), usuario.getSenha());
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
        } catch (IllegalArgumentException e) {
            // Retorna erro 400 com a mensagem da exceção
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Caso haja algum erro inesperado, retorna um erro genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno ao registrar o usuário.");
        }
    }
}
