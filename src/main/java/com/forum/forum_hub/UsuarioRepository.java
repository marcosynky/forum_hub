package com.forum.forum_hub; // Declara o pacote onde a interface está localizada




import com.forum.forum_hub.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método customizado para buscar o usuário pelo email
    Optional<Usuario> findByEmail(String email);
}
