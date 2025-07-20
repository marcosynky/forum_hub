package com.forum.forum_hub.models; // Declara o pacote onde a classe está localizada

import jakarta.persistence.*; // Importa as anotações do JPA para o mapeamento da entidade com o banco de dados
import lombok.AllArgsConstructor; // Importa a anotação do Lombok para gerar um construtor com todos os parâmetros
import lombok.Getter; // Importa a anotação do Lombok para gerar o getter de todos os campos
import lombok.NoArgsConstructor; // Importa a anotação do Lombok para gerar um construtor sem parâmetros
import lombok.Setter; // Importa a anotação do Lombok para gerar o setter de todos os campos

import java.util.List; // Importa a lista que será usada para armazenar os usuários relacionados ao perfil

// A anotação @Entity marca a classe como uma entidade JPA, que será mapeada para uma tabela no banco de dados
@Entity
@Getter // Gera os métodos getter para todos os campos da classe
@Setter // Gera os métodos setter para todos os campos da classe
@NoArgsConstructor // Gera um construtor sem parâmetros
@AllArgsConstructor // Gera um construtor com todos os parâmetros
public class Perfil {

    @Id // Marca o campo 'id' como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A chave primária será gerada automaticamente pelo banco, com auto-incremento
    private Long id; // Identificador único do perfil, do tipo Long

    private String nome; // Nome do perfil, do tipo String

    // Relacionamento muitos-para-muitos (muitos perfis podem ser associados a muitos usuários)
    @ManyToMany(mappedBy = "perfis") // Define o relacionamento muitos-para-muitos, onde 'perfis' é o atributo na classe 'Usuario' que mapeia para esta entidade
    private List<Usuario> usuarios; // Lista de usuários associados a esse perfil
}
