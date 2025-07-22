package com.forum.forum_hub.controller;


import com.forum.forum_hub.models.Topico;// Importe o modelo Topico
import com.forum.forum_hub.service.TopicoService;// Importe o serviço TopicoService
import org.springframework.beans.factory.annotation.Autowired;// Importe o Anotação Autowired
import org.springframework.web.bind.annotation.*;// Importe o Anotação RequestMapping


// Defina a classe TopicoController
@RestController
@RequestMapping("/topics")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Usando @RequestBody para obter os dados JSON da requisição
    @PostMapping("/create")
    public Topico createTopic(@RequestBody Topico topic) { // Requisição POST
        System.out.println("Criando tópico com título: " + topic.getTitulo() + " e descrição: " + topic.getDescription());// Imprime no console
        return topicoService.createTopic(topic.getTitulo(), topic.getDescription());// Retorna o tópico criado
    }
}

