package pizaaria.adapters.out.persistence.entityJpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table (name = "pizza")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class PizzaJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Boolean ativo = true;



}
