package pizaaria.domain.port.in;

import pizaaria.domain.entityDominio.Order;

import java.util.List;

public interface GetFindAllOrderCase {
    List<Order> findAll();
}
