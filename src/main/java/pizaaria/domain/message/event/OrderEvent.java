package pizaaria.domain.message.event;

import pizaaria.domain.dto.CustomerDTO;
import pizaaria.domain.dto.PizzaDTO;
import pizaaria.domain.status.StatusOrder;

import java.time.LocalDateTime;

public record OrderEvent(

        Long orderId,

        CustomerDTO cliente,

        PizzaDTO pizza,

        StatusOrder statusPedido,

        LocalDateTime dataPedido

) {
}