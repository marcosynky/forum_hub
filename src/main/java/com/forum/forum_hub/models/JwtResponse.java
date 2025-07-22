package com.forum.forum_hub.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

// Usando Lombok para simplificar a criação dos métodos Getter, Setter, toString, equals e hashCode
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class JwtResponse {

    private String token;

    // Construtor com o token
    public JwtResponse(String token) {
        this.token = token;
    }
}
