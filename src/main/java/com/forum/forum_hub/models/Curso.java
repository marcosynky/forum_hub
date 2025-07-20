package com.forum.forum_hub.models; // Declara o pacote onde a classe está localizada

import jakarta.persistence.*; // Importa as anotações do JPA para mapeamento de entidades e relacionamento com o banco de dados
import lombok.AllArgsConstructor; // Importa a anotação do Lombok para gerar um construtor com todos os parâmetros
import lombok.Getter; // Importa a anotação do Lombok para gerar o getter de todos os campos
import lombok.NoArgsConstructor; // Importa a anotação do Lombok para gerar um construtor sem parâmetros
import lombok.Setter; // Importa a anotação do Lombok para gerar o setter de todos os campos

import java.util.List; // Importa a lista que será usada para armazenar os tópicos relacionados ao curso

// A anotação @Entity marca a classe como uma entidade JPA que será mapeada para uma tabela no banco de dados
@Entity
@Getter // Gera os métodos getter para todos os campos da classe
@Setter // Gera os métodos setter para todos os campos da classe
@NoArgsConstructor // Gera um construtor sem parâmetros
@AllArgsConstructor // Gera um construtor com todos os parâmetros
public class Curso {

    @Id // Marca o campo 'id' como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A chave primária será gerada automaticamente pelo banco, com auto-incremento
    private Long id; // Identificador único do curso, do tipo Long

    private String nome; // Nome do curso, do tipo String

    private String categoria; // Categoria do curso, do tipo String

    // Relacionamento de um para muitos (um curso pode ter muitos tópicos)
    @OneToMany(mappedBy = "curso") // Define o relacionamento, onde 'curso' é o atributo na classe 'Topico' que mapeia para essa entidade
    private List<Topico> topicos; // Lista de tópicos associados a esse curso
}
