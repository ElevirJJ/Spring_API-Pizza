package pizaaria.adapters.in.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pizaaria.domain.status.StatusOrder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private LocalDateTime dataPedido;

    @JsonProperty("cliente")
    private CustomerDTO clienteDTO;

    @JsonProperty("pizza")
    private PizzaDTO pizzaDTO;

    private StatusOrder statusPedido;
}