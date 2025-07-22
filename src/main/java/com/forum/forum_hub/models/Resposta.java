package com.forum.forum_hub.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A mensagem não pode ser nula")
    private String mensagem;

    // Relacionamento muitos-para-um com Topico
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime dataCriacao = LocalDateTime.now();  // Data de criação com valor padrão

    @NotNull(message = "O autor não pode ser nulo")
    private String autor;

    private boolean solucao;  // Indica se a resposta é a solução para o tópico

    // Método para alterar a data de criação (caso necessário)
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao != null ? dataCriacao : LocalDateTime.now();
    }
}
