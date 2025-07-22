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

    // Método que exibe a página do fórum com a lista de tópicos
    @GetMapping("/forum")
    public String showForum(Model model) {
        List<Topico> topics = topicoRepository.findAll(); // Busca todos os tópicos do banco de dados
        model.addAttribute("topics", topics); // Passa a lista de tópicos para o template
        return "forum"; // Nome do template, onde será exibida a lista de tópicos
    }

    // Método para criar um novo tópico
    @PostMapping("/forum/createTopic")
    public String createTopic(@RequestParam String title, @RequestParam String message, Model model) {
        // Criar um novo tópico
        Topico newTopic = new Topico();
        newTopic.setTitulo(title); // Define o título do tópico
        newTopic.setMensagem(message); // Define a mensagem do tópico


        // Salvar o novo tópico no banco de dados
        topicoRepository.save(newTopic);

        // Redireciona de volta para a página de tópicos
        return "redirect:/forum";
    }
}
