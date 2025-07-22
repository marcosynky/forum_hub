package com.forum.forum_hub;  // Declara o pacote onde a interface está localizada

import com.forum.forum_hub.models.Curso;  // Importa o modelo de dados Curso
import org.springframework.data.jpa.repository.JpaRepository;  // Importa a interface JpaRepository, que fornece métodos prontos para operações no banco de dados
import java.util.List;  // Importa List para possibilitar buscas que retornem mais de um valor

// A interface CursoRepository estende JpaRepository para fornecer os métodos básicos de acesso ao banco de dados
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Método para encontrar cursos pelo nome
    List<Curso> findByNome(String nome);  // Este método é uma consulta personalizada que retorna uma lista de cursos pelo nome

    // Caso precise de outros métodos customizados, basta adicioná-los aqui.
    // Por exemplo, você pode adicionar mais métodos baseados em outros atributos da entidade 'Curso'.

    // Outros métodos que JpaRepository já fornece automaticamente:
    // - List<Curso> findAll();
    // - Optional<Curso> findById(Long id);
    // - void deleteById(Long id);
}
