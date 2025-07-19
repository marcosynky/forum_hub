package com.forum.forum_hub.service;

import com.forum.forum_hub.TopicoRepository;
import com.forum.forum_hub.models.Topico;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> buscarTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    @Transactional
    public Topico criarTopico(Topico topico) {
        Optional<Topico> topicoExistente = topicoRepository.findByTituloAndMensagem(topico.getTitulo(), topico.getMensagem());
        if (topicoExistente.isPresent()) {
            throw new RuntimeException("Tópico já existente com o mesmo título e mensagem.");
        }
        return topicoRepository.save(topico);
    }

    @Transactional
    public Topico atualizarTopico(Long id, Topico topico) {
        Optional<Topico> topicoExistente = topicoRepository.findById(id);
        if (topicoExistente.isEmpty()) {
            throw new RuntimeException("Tópico não encontrado.");
        }
        topico.setId(id);
        return topicoRepository.save(topico);
    }

    @Transactional
    public void deletarTopico(Long id) {
        Optional<Topico> topicoExistente = topicoRepository.findById(id);
        if (topicoExistente.isEmpty()) {
            throw new RuntimeException("Tópico não encontrado.");
        }
        topicoRepository.delete(topicoExistente.get());
    }
}
