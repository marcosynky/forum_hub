package com.forum.forum_hub;  // Declara o pacote onde a interface está localizada

import com.forum.forum_hub.models.Perfil;  // Importa o modelo de dados Perfil
import org.springframework.data.jpa.repository.JpaRepository;  // Importa a interface JpaRepository, que fornece métodos prontos para operações no banco de dados

// A interface PerfilRepository estende JpaRepository para fornecer os métodos básicos de acesso ao banco de dados
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    // JpaRepository já fornece métodos como save, findAll, findById, deleteById e outros automaticamente para a entidade Perfil
    // Aqui, não é necessário escrever métodos adicionais, a menos que haja algum requisito específico.
}
