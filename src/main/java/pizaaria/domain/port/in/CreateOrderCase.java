package pizaaria.domain.port.in;

import pizaaria.adapters.in.web.dto.OrderDTO;
import pizaaria.domain.entityDominio.Order;

public interface CreateOrderCase {
    Order createOrder (OrderDTO orderDTO);
}
