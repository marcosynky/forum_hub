package com.forum.forum_hub;

import com.forum.forum_hub.models.Topico; // Importa o modelo de dados Topico
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository, que fornece métodos prontos para operações no banco de dados

// A interface TopicoRepository estende JpaRepository para fornecer os métodos básicos de acesso ao banco de dados
public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
