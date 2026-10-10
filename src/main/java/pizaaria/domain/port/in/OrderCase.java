package pizaaria.domain.port.in;

import pizaaria.adapters.in.web.dto.OrderDTO;
import pizaaria.domain.entityDominio.Order;

import java.util.List;
import java.util.Optional;

public interface OrderCase {
    Order createOrder (OrderDTO orderDTO);
    Optional<Order> deleteById(Long id);
    Order getById(Long id);
    List<Order> findAll();
    Order update (Long id, OrderDTO orderDTO);
}
