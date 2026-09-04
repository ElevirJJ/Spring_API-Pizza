package pizaaria.domain.port.in;

import pizaaria.adapters.in.web.dto.OrderDTO;
import pizaaria.domain.entityDominio.Order;



public interface UpdateOrderCase {
    Order update (Long id, OrderDTO orderDTO);
}
