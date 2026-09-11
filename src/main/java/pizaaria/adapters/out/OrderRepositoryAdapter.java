package pizaaria.adapters.out;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pizaaria.adapters.out.persistence.entityJpa.OrderJpaEntity;
import pizaaria.adapters.out.repository.OrderRepository;
import pizaaria.domain.entityDominio.Order;
import pizaaria.domain.port.out.OrderRespositoryPort;
import pizaaria.utils.ModelMapperConverter;

import java.util.List;
import java.util.Optional;

@Component
public class OrderRepositoryAdapter implements OrderRespositoryPort {

    private final OrderRepository orderRepository;
    private final ModelMapper mapperConverter;

    public OrderRepositoryAdapter(OrderRepository orderRepository, ModelMapper mapperConverter) {
        this.orderRepository = orderRepository;

        this.mapperConverter = mapperConverter;
    }


    @Override
    public Order save(Order order) {

        OrderJpaEntity orderJpa = mapperConverter.map(order, OrderJpaEntity.class);
        OrderJpaEntity entitySave = orderRepository.save(orderJpa);
        return mapperConverter.map(entitySave, Order.class);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id)
                .map(orderJpa -> mapperConverter.map(orderJpa, Order.class));

    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderJpa -> mapperConverter.map(orderJpa, Order.class)).toList();
    }

    @Override
    public Optional<Order> deleteById(Long id) {
        return orderRepository.findById(id)
                .map(orderJpa -> mapperConverter.map(orderJpa, Order.class));
    }
}
