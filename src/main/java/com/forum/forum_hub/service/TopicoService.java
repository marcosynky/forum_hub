package com.forum.forum_hub.service; // Declara o pacote onde a classe está localizada

import com.forum.forum_hub.TopicoRepository; // Importa o repositório que gerencia as operações com a tabela de tópicos
import com.forum.forum_hub.models.Topico; // Importa a classe Topico, que representa os dados de um tópico no sistema
import jakarta.transaction.Transactional; // Importa a anotação para garantir que os métodos que modificam dados no banco sejam transacionais
import org.springframework.beans.factory.annotation.Autowired; // Permite a injeção de dependências da classe
import org.springframework.stereotype.Service; // Marca a classe como um serviço do Spring, para que ela seja gerenciada pelo Spring

import java.util.List; // Importa o tipo List para retornar uma lista de tópicos
import java.util.Optional; // Importa o tipo Optional para tratar resultados que podem ser nulos

@Service // Marca a classe como um serviço, permitindo que o Spring a gerencie
public class TopicoService {

    @Autowired // A injeção de dependência do repositório é feita automaticamente pelo Spring
    private TopicoRepository topicoRepository; // Referência ao repositório que faz a interação com o banco de dados

    // Método para listar todos os tópicos
    public List<Topico> listarTopicos() {
        return topicoRepository.findAll(); // Retorna todos os tópicos do banco de dados
    }

    // Método para buscar um tópico por ID
    public Optional<Topico> buscarTopicoPorId(Long id) {
        return topicoRepository.findById(id); // Retorna um Optional com o tópico encontrado pelo ID
    }

    // Método para criar um novo tópico
    @Transactional // Garante que as operações de banco de dados sejam feitas em uma transação
    public Topico criarTopico(Topico topico) {
        // Verifica se já existe um tópico com o mesmo título e mensagem
        Optional<Topico> topicoExistente = topicoRepository.findByTituloAndMensagem(topico.getTitulo(), topico.getMensagem());
        if (topicoExistente.isPresent()) { // Se já existir um tópico com esses dados, lança uma exceção
            throw new RuntimeException("Tópico já existente com o mesmo título e mensagem.");
        }
        return topicoRepository.save(topico); // Caso contrário, salva o novo tópico no banco de dados
    }

    // Método para atualizar um tópico existente
    @Transactional // Garante que a atualização seja realizada dentro de uma transação
    public Topico atualizarTopico(Long id, Topico topico) {
        Optional<Topico> topicoExistente = topicoRepository.findById(id); // Verifica se o tópico com o ID existe
        if (topicoExistente.isEmpty()) { // Se não existir, lança uma exceção
            throw new RuntimeException("Tópico não encontrado.");
        }
        topico.setId(id); // Atualiza o ID do tópico com o ID fornecido
        return topicoRepository.save(topico); // Salva o tópico atualizado no banco de dados
    }

    // Método para deletar um tópico
    @Transactional // Garante que a exclusão do tópico seja realizada dentro de uma transação
    public void deletarTopico(Long id) {
        Optional<Topico> topicoExistente = topicoRepository.findById(id); // Verifica se o tópico com o ID existe
        if (topicoExistente.isEmpty()) { // Se não existir, lança uma exceção
            throw new RuntimeException("Tópico não encontrado.");
        }
        topicoRepository.delete(topicoExistente.get()); // Se existir, deleta o tópico
    }
}
