package pizaaria.domain.message.event;

import pizaaria.domain.status.StatusOrder;

import java.time.LocalDateTime;

public record OrderEvent(

        Long orderId,
        Long customerId,
        Long pizzaId,
        StatusOrder statusPedido,
        LocalDateTime dataPedido

) {
}
