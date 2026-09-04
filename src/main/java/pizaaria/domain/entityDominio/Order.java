package pizaaria.domain.entityDominio;

import lombok.*;
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

    private Pizza pizza;
    private Customer customer;

    private StatusOrder statusPedido;
}