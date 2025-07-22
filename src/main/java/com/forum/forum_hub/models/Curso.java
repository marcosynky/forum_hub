package com.forum.forum_hub.models;

import jakarta.persistence.*;  // Importa as anotações de persistência do JPA
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// A classe Curso é uma entidade JPA, que será mapeada para uma tabela no banco de dados
@Entity
public class Curso {

    // Define o identificador único para a entidade Curso. A anotação @Id indica que o campo id é a chave primária.
    // A anotação @GeneratedValue define que o valor de id será gerado automaticamente.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // O ID será gerado automaticamente, com estratégia de incremento no banco de dados
    private Long id;

    // Define o nome do curso como um atributo da entidade. Ele será mapeado para uma coluna na tabela de cursos.
    @Setter
    @Getter
    @NotBlank(message = "O nome do curso não pode ser vazio")  // Valida se o nome não é vazio
    @Size(min = 3, max = 100, message = "O nome do curso deve ter entre 3 e 100 caracteres")  // Valida o tamanho do nome
    private String nome;  // Nome do curso

    // Construtor padrão exigido pelo JPA
    public Curso() {}

    // Construtor para criar um novo Curso
    public Curso(String nome) {
        this.nome = nome;
    }

    // Override do método toString() para representar o objeto em formato de String
    @Override
    public String toString() {
        return "Curso{id=" + id + ", nome='" + nome + "'}";
    }

    // Override dos métodos equals() e hashCode() para garantir comparações corretas de objetos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return id != null && id.equals(curso.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
