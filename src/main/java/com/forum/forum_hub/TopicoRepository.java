package com.forum.forum_hub;


import com.forum.forum_hub.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Método para buscar tópico por título e mensagem
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);
}