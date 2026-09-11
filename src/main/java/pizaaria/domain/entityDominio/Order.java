package pizaaria.domain.entityDominio;

import lombok.*;
import pizaaria.adapters.out.persistence.entityJpa.CustomerJpaEntity;
import pizaaria.adapters.out.persistence.entityJpa.PizzaJpaEntity;
import pizaaria.domain.status.StatusOrder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    private Long id;
    private LocalDateTime dataPedido;
    private Boolean ativo = true;

    private PizzaJpaEntity pizza;
    private CustomerJpaEntity customer;

    private StatusOrder statusPedido;

}
