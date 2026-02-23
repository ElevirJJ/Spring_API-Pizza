package pizaaria.domain.entity;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "cliente")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;

    @Embedded
    private Endereco endereco;


}
