package com.forum.forum_hub; // Declara o pacote onde a interface está localizada



import com.forum.forum_hub.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para buscar o usuário pelo nome
    Optional<Usuario> findByNome(String nome); // Método que irá buscar o usuário pelo nome
}
