package pizaaria.domain.port.in;

import pizaaria.domain.entityDominio.Order;

import java.util.Optional;

public interface DeleteOrderCase {
    Optional<Order> deleteById(Long id);
}
