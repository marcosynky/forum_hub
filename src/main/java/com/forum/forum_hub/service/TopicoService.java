package com.forum.forum_hub.service;

import com.forum.forum_hub.TopicoRepository;
import com.forum.forum_hub.models.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    // Método para criar um novo tópico
    public Topico createTopic(String titulo, String description) {
        Topico topico = new Topico();
        topico.setTitulo(titulo);
        topico.setDescription(description);
        return topicoRepository.save(topico);
    }

    // Método para atualizar um tópico
    public Topico updateTopic(Long id, String titulo, String description) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isPresent()) {
            Topico topico = topicoOpt.get();
            topico.setTitulo(titulo);
            topico.setDescription(description);
            return topicoRepository.save(topico);
        } else {
            throw new RuntimeException("Tópico não encontrado com o ID: " + id);
        }
    }

    // Método para deletar um tópico
    public String deleteTopic(Long id) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isPresent()) {
            topicoRepository.deleteById(id);
            return "Tópico deletado com sucesso!";
        } else {
            throw new RuntimeException("Tópico não encontrado com o ID: " + id);
        }
    }
}
