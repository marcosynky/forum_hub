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

    @PostMapping
    public ResponseEntity<Topico> criarTopico(@RequestBody @Valid Topico topico) {
        return ResponseEntity.ok(topicoService.criarTopico(topico));
    }

    @GetMapping
    public ResponseEntity<List<Topico>> listarTopicos() {
        return ResponseEntity.ok(topicoService.listarTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> buscarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.buscarTopicoPorId(id);
        return topico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @RequestBody @Valid Topico topico) {
        return ResponseEntity.ok(topicoService.atualizarTopico(id, topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        topicoService.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }
}