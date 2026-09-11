package pizaaria.adapters.out.persistence.entityJpa;

import jakarta.persistence.*;
import lombok.*;
import pizaaria.domain.status.StatusOrder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "pedido")
public class OrderJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataPedido;
    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private PizzaJpaEntity pizzaJpaEntity;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private CustomerJpaEntity customerJpaEntity;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusPedido;


}
