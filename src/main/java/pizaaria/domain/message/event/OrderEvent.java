package pizaaria.domain.message.event;

import pizaaria.domain.dto.CustomerDTO;
import pizaaria.domain.dto.PizzaDTO;
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