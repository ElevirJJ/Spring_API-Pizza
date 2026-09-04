package pizaaria.domain.port.out;

import pizaaria.domain.entityDominio.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRespositoryPort{
    Order save (Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    Optional<Order> deleteById(Long id);
}
