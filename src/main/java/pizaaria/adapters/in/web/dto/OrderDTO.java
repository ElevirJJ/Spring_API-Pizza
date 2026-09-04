package pizaaria.adapters.in.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import pizaaria.domain.status.StatusOrder;

import java.time.LocalDateTime;

public record OrderDTO(LocalDateTime dataPedido, @JsonProperty("pizza") pizaaria.domain.entity.Customer pizzaDTO, @JsonProperty("cliente") pizaaria.domain.entity.Pizza clienteDTO, StatusOrder statusPedido) {

}
