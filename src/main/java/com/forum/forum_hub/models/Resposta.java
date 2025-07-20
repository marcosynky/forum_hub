package com.forum.forum_hub.models; // Declara o pacote onde a classe está localizada

import jakarta.persistence.*; // Importa as anotações do JPA para mapeamento da entidade com o banco de dados
import lombok.AllArgsConstructor; // Importa a anotação do Lombok para gerar um construtor com todos os parâmetros
import lombok.Getter; // Importa a anotação do Lombok para gerar o getter de todos os campos
import lombok.NoArgsConstructor; // Importa a anotação do Lombok para gerar um construtor sem parâmetros
import lombok.Setter; // Importa a anotação do Lombok para gerar o setter de todos os campos

import java.time.LocalDateTime; // Importa a classe LocalDateTime para manipulação de datas e horas

// A anotação @Entity marca a classe como uma entidade JPA, que será mapeada para uma tabela no banco de dados
@Entity
@Getter // Gera os métodos getter para todos os campos da classe
@Setter // Gera os métodos setter para todos os campos da classe
@NoArgsConstructor // Gera um construtor sem parâmetros
@AllArgsConstructor // Gera um construtor com todos os parâmetros
public class Resposta {

    @Id // Marca o campo 'id' como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A chave primária será gerada automaticamente pelo banco com auto-incremento
    private Long id; // Identificador único da resposta, do tipo Long

    private String mensagem; // A mensagem da resposta, do tipo String

    // Relacionamento muitos-para-um (muitas respostas podem pertencer a um tópico)
    @ManyToOne // Indica que muitas respostas podem estar associadas a um único tópico
    @JoinColumn(name = "topico_id") // Especifica o nome da coluna de junção no banco de dados que mapeia a relação
    private Topico topico; // Relacionamento com a entidade Topico, indicando a qual tópico a resposta pertence

    private LocalDateTime dataCriacao = LocalDateTime.now(); // A data e hora de criação da resposta, com valor padrão de agora

    private String autor; // O autor da resposta, do tipo String (nome ou identificador do autor)

    private boolean solucao; // Indica se a resposta é a solução para o tópico (booleano)
}
