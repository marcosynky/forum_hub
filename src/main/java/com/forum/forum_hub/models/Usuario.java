package com.forum.forum_hub.models; // Declara o pacote onde a classe está localizada

import jakarta.persistence.*; // Importa as anotações do JPA para mapeamento da entidade com o banco de dados
import lombok.AllArgsConstructor; // Importa a anotação do Lombok para gerar um construtor com todos os parâmetros
import lombok.Getter; // Importa a anotação do Lombok para gerar o getter de todos os campos
import lombok.NoArgsConstructor; // Importa a anotação do Lombok para gerar um construtor sem parâmetros
import lombok.Setter; // Importa a anotação do Lombok para gerar o setter de todos os campos

import java.util.List; // Importa a lista que será usada para armazenar os perfis associados ao usuário

// A anotação @Entity marca a classe como uma entidade JPA, que será mapeada para uma tabela no banco de dados
@Entity
@Getter // Gera os métodos getter para todos os campos da classe
@Setter // Gera os métodos setter para todos os campos da classe
@NoArgsConstructor // Gera um construtor sem parâmetros
@AllArgsConstructor // Gera um construtor com todos os parâmetros
public class Usuario {

    @Id // Marca o campo 'id' como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A chave primária será gerada automaticamente pelo banco com auto-incremento
    private Long id; // Identificador único do usuário, do tipo Long

    private String nome; // Nome do usuário, do tipo String

    private String email; // E-mail do usuário, do tipo String

    private String senha; // Senha do usuário, do tipo String

    // Relacionamento muitos-para-muitos (um usuário pode ter muitos perfis e um perfil pode ser atribuído a muitos usuários)
    @ManyToMany // Define um relacionamento muitos-para-muitos entre usuário e perfil
    @JoinTable( // Define uma tabela de junção para gerenciar o relacionamento muitos-para-muitos
            name = "usuario_perfil", // Nome da tabela de junção no banco de dados
            joinColumns = @JoinColumn(name = "usuario_id"), // Nome da coluna de junção na tabela de junção que aponta para a chave primária de Usuario
            inverseJoinColumns = @JoinColumn(name = "perfil_id") // Nome da coluna de junção na tabela de junção que aponta para a chave primária de Perfil
    )
    private List<Perfil> perfis; // Lista de perfis associados a esse usuário, do tipo List<Perfil>
}
