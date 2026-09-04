package pizaaria.domain.port.in;

import pizaaria.domain.entityDominio.Order;

import java.util.Optional;

public interface GetOrderCase {
    Optional<Order> getById(Long id);
}
