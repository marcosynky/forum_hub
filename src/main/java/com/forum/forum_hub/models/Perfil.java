package com.forum.forum_hub.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Geração automática do ID
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "perfil_usuario",  // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "perfil_id"),  // Coluna de chave estrangeira para Perfil
            inverseJoinColumns = @JoinColumn(name = "usuario_id")  // Coluna de chave estrangeira para User
    )
    private List<User> usuarios;

    // Construtor padrão
    public Perfil() {}

    // Construtor com parâmetros
    public Perfil(Long id, List<User> usuarios) {
        this.id = id;
        this.usuarios = usuarios;
    }

    // Métodos getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<User> usuarios) {
        this.usuarios = usuarios;
    }
}
