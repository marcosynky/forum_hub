package com.forum.forum_hub.service;

import com.forum.forum_hub.UserRepository;
import com.forum.forum_hub.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private UserRepository userRepository;  // Repositório para a entidade User

    @Autowired
    private PasswordEncoder passwordEncoder; // Usando o PasswordEncoder para criptografar a senha

    @Override
    public void run(String... args) throws Exception {
        // Email para testar
        String email = "7W6m1@example.com";

        // Verifica se o usuário já existe no banco de dados
        if (!userRepository.existsByEmail(email)) {
            // Se o usuário não existir, cria um novo usuário
            User usuario = new User();
            usuario.setEmail(email);
            usuario.setNome("Teste");  // Nome do usuário
            usuario.setSenha(passwordEncoder.encode("senhaDeTeste123")); // Definindo uma senha válida

            // Salva o novo usuário no banco de dados
            userRepository.save(usuario);

            // Log para indicar que o usuário foi criado
            logger.info("Usuário {} criado com sucesso.", email);
        } else {
            // Log para indicar que o usuário já existe
            logger.info("Usuário {} já existe no banco de dados.", email);
        }
    }
}
