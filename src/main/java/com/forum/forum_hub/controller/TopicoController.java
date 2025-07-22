package com.forum.forum_hub.controller;

import com.forum.forum_hub.models.Topico;
import com.forum.forum_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Endpoint para criar um novo tópico
    @PostMapping
    public ResponseEntity<Topico> criarTopico(@RequestBody @Valid Topico topico) {
        // Validação de entrada automática feita pelo @Valid
        Topico createdTopico = topicoService.criarTopico(topico);
        return ResponseEntity.status(201).body(createdTopico);  // Retorna o tópico criado com status 201 (Created)
    }

    // Endpoint para listar todos os tópicos
    @GetMapping
    public ResponseEntity<List<Topico>> listarTopicos() {
        List<Topico> topicos = topicoService.listarTopicos();
        return ResponseEntity.ok(topicos);  // Retorna a lista de tópicos com status 200 (OK)
    }

    // Endpoint para buscar um tópico específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Topico> buscarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.buscarTopicoPorId(id);
        // Se o tópico for encontrado, retorna o tópico com status 200 (OK), caso contrário retorna status 404 (Not Found)
        return topico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
    }

    // Endpoint para atualizar um tópico existente
    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @RequestBody @Valid Topico topico) {
        // Chama o serviço para atualizar o tópico e retorna o tópico atualizado com status 200 (OK)
        Topico updatedTopico = topicoService.atualizarTopico(id, topico);
        return ResponseEntity.ok(updatedTopico);
    }

    // Endpoint para deletar um tópico pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        topicoService.deletarTopico(id);
        // Retorna um status 204 (No Content), indicando que a operação foi bem-sucedida mas não há conteúdo a ser retornado
        return ResponseEntity.noContent().build();
    }
}
