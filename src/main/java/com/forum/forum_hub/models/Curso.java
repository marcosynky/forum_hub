package com.forum.forum_hub.models; // Declara o pacote onde a classe está localizada



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getters e Setters
    @Setter
    @Getter
    private String nome;  // Nome do curso

}

