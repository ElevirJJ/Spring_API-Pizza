package pizaaria.adapters.out.persistence;

import org.springframework.stereotype.Component;
import pizaaria.domain.entityDominio.Order;
import pizaaria.domain.port.out.OrderRespositoryPort;

import java.util.List;
import java.util.Optional;


@Component
public class OrderRepositoryAdapter implements OrderRespositoryPort {

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public Optional<Order> deleteById(Long id) {
        return Optional.empty();
    }
}
