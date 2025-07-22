package com.forum.forum_hub.service;

import com.forum.forum_hub.UserRepository;
import com.forum.forum_hub.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;  // Importa o PasswordEncoder
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Injeção do PasswordEncoder

    public User criarUsuario(String email, String nome, String senha) {
        // Validação simples para garantir que o email não seja nulo ou vazio
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }

        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }

        // Criptografando a senha antes de salvar no banco
        String senhaCriptografada = passwordEncoder.encode(senha);

        // Criação do usuário
        User usuario = new User();
        usuario.setEmail(email);
        usuario.setNome(nome);
        usuario.setSenha(senhaCriptografada);  // Armazenando a senha criptografada

        return userRepository.save(usuario);  // Salva o usuário no banco de dados
    }
}
