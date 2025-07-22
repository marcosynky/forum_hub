package com.forum.forum_hub;  // Declara o pacote onde a interface está localizada

import com.forum.forum_hub.models.Resposta;  // Importa o modelo de dados Resposta
import org.springframework.data.jpa.repository.JpaRepository;  // Importa a interface JpaRepository, que fornece métodos prontos para operações no banco de dados

// A interface RespostaRepository estende JpaRepository para fornecer os métodos básicos de acesso ao banco de dados
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    // JpaRepository já fornece métodos como save, findAll, findById, deleteById e outros automaticamente para a entidade Resposta
    // Aqui, não é necessário escrever métodos adicionais, a menos que haja algum requisito específico.
}
