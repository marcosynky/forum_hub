package com.forum.forum_hub.models; // Declara o pacote onde a classe está localizada



import jakarta.persistence.Entity; // Anotação para mapear a classe para uma tabela no banco de dados
import jakarta.persistence.GeneratedValue; // Geração automática do valor da chave primária
import jakarta.persistence.GenerationType; // Estratégias para a geração da chave primária
import jakarta.persistence.Id; // Define a chave primária da entidade
import jakarta.validation.constraints.NotNull; // Importa a anotação para validação de campos obrigatórios
import lombok.Getter; // Lombok para gerar automaticamente os métodos getter
import lombok.Setter; // Lombok para gerar automaticamente os métodos setter
import lombok.AllArgsConstructor; // Lombok para gerar o construtor com todos os parâmetros
import lombok.NoArgsConstructor; // Lombok para gerar o construtor sem parâmetros

@Entity // Marca a classe como uma entidade JPA, que será mapeada para uma tabela no banco de dados
@Getter // Lombok: gera automaticamente os getters para todos os campos
@Setter // Lombok: gera automaticamente os setters para todos os campos
@NoArgsConstructor // Lombok: gera o construtor sem parâmetros (necessário para JPA)
@AllArgsConstructor // Lombok: gera o construtor com todos os parâmetros
public class Topico {

    @Id // Define o campo 'id' como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A chave primária será gerada automaticamente, com incremento
    private Long id; // Identificador único do tópico, do tipo Long

    @NotNull(message = "O título não pode ser nulo.") // Valida que o título não seja nulo
    private String titulo; // Título do tópico

    @NotNull(message = "A mensagem não pode ser nula.") // Valida que a mensagem não seja nula
    private String mensagem; // Mensagem do tópico

    @NotNull(message = "A descrição não pode ser nula.") // Valida que a descrição não seja nula
    private String descricao; // Descrição do tópico

    // Não é mais necessário escrever os getters e setters, pois Lombok já gerou automaticamente
}
