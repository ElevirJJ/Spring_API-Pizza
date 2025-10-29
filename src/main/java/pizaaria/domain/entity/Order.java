package pizaaria.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import pizaaria.domain.dto.OrderDTO;
import pizaaria.domain.status.StatusOrder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "pedido")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataPedido;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Customer cliente;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusPedido;

    public Order(OrderDTO pedidoDto , Pizza pizza, Customer cliente) {
        this.dataPedido = pedidoDto.dataPedido();
        this.pizza = pizza;
        this.cliente = cliente;
        this.statusPedido = pedidoDto.statusPedido();
    }

}
