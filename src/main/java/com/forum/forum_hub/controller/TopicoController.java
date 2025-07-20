package com.forum.forum_hub.controller; // Declara o pacote onde a classe está localizada

import com.forum.forum_hub.models.Topico; // Importa o modelo de dados Topico
import com.forum.forum_hub.service.TopicoService; // Importa o serviço que lida com a lógica de negócios dos tópicos
import jakarta.validation.Valid; // Importa a anotação @Valid para validação dos dados de entrada
import org.springframework.beans.factory.annotation.Autowired; // Permite a injeção de dependência do serviço no controlador
import org.springframework.http.ResponseEntity; // Importa a classe que ajuda a construir as respostas HTTP
import org.springframework.web.bind.annotation.*; // Importa as anotações para mapear os endpoints HTTP (GET, POST, PUT, DELETE)

import java.util.List; // Importa a lista que será retornada com os tópicos
import java.util.Optional; // Importa a classe Optional para tratar respostas que podem ser nulas

@RestController // Define essa classe como um controlador REST, que lida com as requisições HTTP
@RequestMapping("/topicos") // Define o caminho base para os endpoints desta classe (exemplo: /topicos)
public class TopicoController {

    @Autowired // Injeção de dependência automática do serviço TopicoService
    private TopicoService topicoService;

    // Endpoint para criar um novo tópico
    @PostMapping // Mapeia o método HTTP POST para este endpoint
    public ResponseEntity<Topico> criarTopico(@RequestBody @Valid Topico topico) {
        // Chama o serviço para criar o tópico e retorna o tópico criado com um status 200 OK
        return ResponseEntity.ok(topicoService.criarTopico(topico));
    }

    // Endpoint para listar todos os tópicos
    @GetMapping // Mapeia o método HTTP GET para este endpoint
    public ResponseEntity<List<Topico>> listarTopicos() {
        // Chama o serviço para listar todos os tópicos e retorna os tópicos com um status 200 OK
        return ResponseEntity.ok(topicoService.listarTopicos());
    }

    // Endpoint para buscar um tópico específico pelo ID
    @GetMapping("/{id}") // Mapeia o método HTTP GET para este endpoint com um parâmetro de ID na URL
    public ResponseEntity<Topico> buscarTopico(@PathVariable Long id) {
        // Chama o serviço para buscar o tópico pelo ID
        Optional<Topico> topico = topicoService.buscarTopicoPorId(id);
        // Se o tópico for encontrado, retorna o tópico com status 200 OK. Caso contrário, retorna status 404 (não encontrado)
        return topico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar um tópico existente
    @PutMapping("/{id}") // Mapeia o método HTTP PUT para este endpoint com um parâmetro de ID na URL
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @RequestBody @Valid Topico topico) {
        // Chama o serviço para atualizar o tópico e retorna o tópico atualizado com um status 200 OK
        return ResponseEntity.ok(topicoService.atualizarTopico(id, topico));
    }

    // Endpoint para deletar um tópico pelo ID
    @DeleteMapping("/{id}") // Mapeia o método HTTP DELETE para este endpoint com um parâmetro de ID na URL
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        // Chama o serviço para deletar o tópico
        topicoService.deletarTopico(id);
        // Retorna um status 204 No Content, indicando que a operação foi bem-sucedida, mas não há conteúdo a ser retornado
        return ResponseEntity.noContent().build();
    }
}
