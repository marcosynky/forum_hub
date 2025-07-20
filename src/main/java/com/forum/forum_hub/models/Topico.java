package com.forum.forum_hub.models; // Declara o pacote onde a classe está localizada

import jakarta.persistence.*; // Importa as anotações do JPA para mapeamento da entidade com o banco de dados
import lombok.AllArgsConstructor; // Importa a anotação do Lombok para gerar um construtor com todos os parâmetros
import lombok.Getter; // Importa a anotação do Lombok para gerar o getter de todos os campos
import lombok.NoArgsConstructor; // Importa a anotação do Lombok para gerar um construtor sem parâmetros
import lombok.Setter; // Importa a anotação do Lombok para gerar o setter de todos os campos

import java.time.LocalDateTime; // Importa a classe LocalDateTime para manipulação de datas e horas
import java.util.List; // Importa a lista que será usada para armazenar as respostas associadas ao tópico

// A anotação @Entity marca a classe como uma entidade JPA, que será mapeada para uma tabela no banco de dados
@Entity
@Getter // Gera os métodos getter para todos os campos da classe
@Setter // Gera os métodos setter para todos os campos da classe
@NoArgsConstructor // Gera um construtor sem parâmetros
@AllArgsConstructor // Gera um construtor com todos os parâmetros
public class Topico {

    @Id // Marca o campo 'id' como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A chave primária será gerada automaticamente pelo banco com auto-incremento
    private Long id; // Identificador único do tópico, do tipo Long

    private String titulo; // Título do tópico, do tipo String

    private String mensagem; // A mensagem/descritivo do tópico, do tipo String

    private LocalDateTime dataCriacao = LocalDateTime.now(); // A data e hora de criação do tópico, com valor padrão de agora

    // A anotação @Enumerated é usada para mapear um valor enum no banco de dados.
    // O EnumType.STRING indica que o valor do enum será armazenado como uma String no banco (ex: "ABERTO", "FECHADO").
    @Enumerated(EnumType.STRING)
    private EstadoTopico estado = EstadoTopico.ABERTO; // Estado do tópico (se está aberto ou fechado), com valor padrão "ABERTO"

    private String autor; // O autor do tópico, do tipo String

    // Relacionamento muitos-para-um (muitas respostas podem estar associadas a um único tópico)
    @ManyToOne // Define que muitos tópicos podem estar associados a um único curso
    @JoinColumn(name = "curso_id") // Define o nome da coluna que fará a junção com a tabela de cursos
    private Curso curso; // Relacionamento com a entidade Curso, indicando o curso associado ao tópico

    // Relacionamento de um para muitos (um tópico pode ter muitas respostas)
    @OneToMany(mappedBy = "topico") // Define o relacionamento, onde 'topico' é o atributo na classe 'Resposta' que mapeia para esta entidade
    private List<Resposta> respostas; // Lista de respostas associadas a esse tópico
}

// Enum para definir o estado do tópico, com valores possíveis: ABERTO ou FECHADO
enum EstadoTopico {
    ABERTO, FECHADO
}
