package com.forum.forum_hub.service;


import com.forum.forum_hub.UsuarioRepository;
import com.forum.forum_hub.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository; // Repositório para a entidade Usuario

    @Autowired
    private PasswordEncoder passwordEncoder; // Usando o PasswordEncoder para criptografar a senha

    @Override
    public void run(String... args) throws Exception {
        // Cria o usuário com a senha criptografada
        Usuario usuario = new Usuario();
        usuario.setNome("marcosynky");
        usuario.setEmail("marcosynky@gmail.com"); // Um email fictício
        usuario.setSenha(passwordEncoder.encode("samuel1978")); // Criptografando a senha com BCrypt

        // Salva o usuário no banco de dados
        usuarioRepository.save(usuario);
    }
}

