package pizaaria.adapters.out.persistence.entityJpa;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "cliente")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private Boolean ativo = true;

    @Embedded
    private EnderecoJpaEntity enderecoJpaEntity;


}
