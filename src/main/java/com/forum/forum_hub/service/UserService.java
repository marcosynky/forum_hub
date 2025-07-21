package com.forum.forum_hub.service;


import com.forum.forum_hub.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.forum.forum_hub.models.Usuario;



@Service
public class UserService {

    @Autowired
    private UsuarioRepository userRepository;

    // Verifica se o email já está registrado
    public boolean isEmailAlreadyRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // Registra um novo usuário
    public void registerUser(Usuario usuario) {
        if (isEmailAlreadyRegistered(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já registrado");
        }

        // Salva o novo usuário no banco de dados
        userRepository.save(usuario);
    }
}
