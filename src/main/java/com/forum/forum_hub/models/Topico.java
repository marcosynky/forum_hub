package com.forum.forum_hub.models;

import jakarta.persistence.*;

@Entity // Indica que essa classe representa uma tabela no banco de dados
@Table(name = "topico")// Define o nome da tabela
public class Topico { // Declara a classe Topico
    @Id // Define a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Define a estratégia de geração da chave primária
    private Long id; // Declara o atributo id

    @Column(name = "titulo", nullable = false) // Define o nome da coluna
    private String titulo;// Declara o atributo titulo

    @Column(name = "description") // Define o nome da coluna
    private String description; // Declara o atributo description

    @Column(name = "mensagem", nullable = false) // Define o nome da coluna
    private String mensagem; // Declara o atributo mensagem

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
