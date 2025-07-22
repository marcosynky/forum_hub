package com.forum.forum_hub.service;

import com.forum.forum_hub.TopicoRepository; // Importa a interface TopicoRepository
import com.forum.forum_hub.models.Topico; // Importa o modelo de dados Topico
import org.springframework.beans.factory.annotation.Autowired; // Importa o Anotação Autowired
import org.springframework.stereotype.Service; // Importa o Anotação Service

// Declara a classe TopicoService
@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicRepository; // Declara o atributo topicRepository

    public Topico createTopic(String title, String description) {
        // Cria um novo tópico
        Topico topic = new Topico();

        // Define o título e a descrição do tópico
        topic.setTitulo(title);
        topic.setDescription(description);

        // Salva o tópico no banco de dados
        return topicRepository.save(topic); // Retorna o tópico salvo
    }
}
