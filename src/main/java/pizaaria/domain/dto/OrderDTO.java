package pizaaria.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import pizaaria.domain.entity.Order;
import pizaaria.domain.status.StatusOrder;

import java.time.LocalDateTime;

public record OrderDTO(LocalDateTime dataPedido, @JsonProperty("pizza") PizzaDTO pizzaDTO, @JsonProperty("cliente") CustomerDTO clienteDTO, StatusOrder statusPedido) {
    public OrderDTO(Order pedido){
        this(pedido.getDataPedido(), new PizzaDTO(pedido.getPizza()), new CustomerDTO(pedido.getCliente()), pedido.getStatusPedido());
    }
}
