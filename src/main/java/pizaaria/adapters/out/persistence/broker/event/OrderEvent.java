package pizaaria.adapters.out.persistence.broker.event;

import pizaaria.adapters.in.web.dto.CustomerDTO;
import pizaaria.adapters.in.web.dto.PizzaDTO;
import pizaaria.domain.status.StatusOrder;

import java.time.LocalDateTime;

public record OrderEvent(

        Long orderId,

        Long clienteId,

        Long pizzaId,

        StatusOrder statusPedido,

        LocalDateTime dataPedido

) {
}