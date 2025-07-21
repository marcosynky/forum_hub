package com.forum.forum_hub.controller;



import com.forum.forum_hub.TopicoRepository;
import com.forum.forum_hub.models.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class ForumController {

    @Autowired
    private TopicoRepository topicoRepository;  // Repositório para acessar a base de dados

    @GetMapping("/forum")
    public String showForum(Model model) {
        Topico topic = new Topico();
        topic.setDescricao("Descrição do tópico");
        topic.setTitulo("Título do tópico");
        topic.setMensagem("Mensagem do tópico");

        model.addAttribute("topic", topic);
        return "forum"; // Nome do seu template
    }


        @PostMapping("/forum/createTopic")
    public String createTopic(@RequestParam String title, @RequestParam String description, Model model) {
        // Criar novo tópico
        Topico newTopic = new Topico();
        newTopic.setTitulo(title);
        newTopic.setMensagem(description); // Define a data de criação como o momento atual

        // Salvar no banco de dados
        topicoRepository.save(newTopic);

        // Redirecionar de volta para a página de tópicos
        return "redirect:/forum";
    }
}
