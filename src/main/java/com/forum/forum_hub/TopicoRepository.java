package com.forum.forum_hub; // Declara o pacote onde a interface está localizada

import com.forum.forum_hub.models.Topico; // Importa o modelo de dados Topico
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository, que fornece métodos prontos para operações no banco de dados

import java.util.Optional; // Importa a classe Optional, usada para lidar com valores que podem ser nulos

// A interface TopicoRepository estende JpaRepository para fornecer os métodos básicos de acesso ao banco de dados
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Método para buscar tópico por título e mensagem
    // O Spring Data JPA irá automaticamente gerar a implementação para este método
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);
}
