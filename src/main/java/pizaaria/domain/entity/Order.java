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
    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusPedido;


}
