package com.forum.forum_hub;


import com.forum.forum_hub.models.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
}