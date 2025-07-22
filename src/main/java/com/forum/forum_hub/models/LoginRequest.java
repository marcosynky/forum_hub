package com.forum.forum_hub.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

// Usando Lombok para gerar os métodos Getter, Setter, toString, equals e hashCode
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoginRequest {

    private String email;  // Atributo que armazena o email do usuário
    private String senha;  // Atributo que armazena a senha do usuário
}
