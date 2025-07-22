package com.forum.forum_hub; // Declara o pacote onde a interface está localizada

import com.forum.forum_hub.models.User; // Importa o modelo de dados User
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository, que fornece métodos prontos para operações no banco de dados

import java.util.Optional; // Importa a classe Optional, usada para lidar com valores que podem ser nulos

// A interface UserRepository estende JpaRepository para fornecer os métodos básicos de acesso ao banco de dados
public interface UserRepository extends JpaRepository<User, Long> {

    // Método customizado para buscar o usuário pelo email
    Optional<User> findByEmail(String email);

    // Método para verificar se o usuário com o email já existe
    boolean existsByEmail(String email);  // Ou outro tipo de ID que você esteja usando
}
