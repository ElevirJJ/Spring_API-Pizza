package pizaaria.application.service;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pizaaria.adapters.in.web.dto.CustomerDTO;
import pizaaria.adapters.in.web.dto.OrderDTO;
import pizaaria.adapters.in.web.dto.PizzaDTO;
import pizaaria.domain.entityDominio.Order;
import pizaaria.adapters.out.persistence.broker.event.OrderEvent;
import pizaaria.adapters.out.persistence.broker.produce.OrderProduce;
import pizaaria.domain.exception.NotFoundException;
import pizaaria.domain.port.in.*;
import pizaaria.domain.port.out.OrderRespositoryPort;
import pizaaria.domain.status.StatusOrder;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements CreateOrderCase, DeleteOrderCase, GetFindAllOrderCase,GetOrderCase, UpdateOrderCase {

    private final PizzaService pizzaService;
    private final CustomerService customerService;
    private final OrderProduce orderProduce;
    private final OrderRespositoryPort orderRespositoryPort;

    public OrderService(PizzaService pizzaService, CustomerService customerService, OrderProduce orderProduce, OrderRespositoryPort orderRespositoryPort) {
        this.pizzaService = pizzaService;
        this.customerService = customerService;
        this.orderProduce = orderProduce;
        this.orderRespositoryPort = orderRespositoryPort;
    }


    @Override
    public Order createOrder(OrderDTO orderDTO) {
        var pizzaPost = pizzaService.buscarPorIdPizza(orderDTO.pizzaDTO().id());
        var customPost =customerService.buscarClienteID(orderDTO.clienteDTO().id());

        var status = orderDTO.statusPedido() != null ? orderDTO.statusPedido() : StatusOrder.PENDENTE;
        var date = orderDTO.dataPedido() != null ? orderDTO.dataPedido() : LocalDateTime.now();

        Order newOrder = Order.builder()
                .pizza(pizzaPost)
                .customer(customPost)
                .statusPedido(status)
                .dataPedido(date)
                .ativo(true)
                .build();

        var orderSave = orderRespositoryPort.save(newOrder);


        CustomerDTO customerDTO = new CustomerDTO(
                orderSave.getCustomer().getId(),
                orderSave.getCustomer().getNome(),
                orderSave.getCustomer().getTelefone(),
                orderSave.getCustomer().getEnderecoJpaEntity()
        );


        PizzaDTO pizzaDTO = new PizzaDTO(
                orderSave.getPizza().getId(),
                orderSave.getPizza().getNome(),
                orderSave.getPizza().getDescricao()
        );


        OrderEvent event = new OrderEvent(
                orderSave.getId(),
                customerDTO,
                pizzaDTO,
                orderSave.getStatusPedido(),
                orderSave.getDataPedido()
        );


        orderProduce.enviarOrder(event);

        return orderSave;
    }

    @Override
    public Optional<Order> deleteById(Long id) {
        return orderRespositoryPort.deleteById(id)
                .map(order -> {
                    order.setAtivo(false);
                    return orderRespositoryPort.save(order);
                });

    }

    @Override
    public Optional<Order> getById(Long id) {
        return orderRespositoryPort.findById(id);
    }

    @Override
    public Order update(Long id, OrderDTO dto) {
        Order orderEntity = getById(id).orElseThrow(() -> new NotFoundException("id não existi"));

        orderEntity.setDataPedido(dto.dataPedido() != null ? dto.dataPedido() : orderEntity.getDataPedido());
        orderEntity.setPizza(dto.pizzaDTO() != null ? pizzaService.buscarPorIdPizza(dto.pizzaDTO().id()) : orderEntity.getPizza());
        orderEntity.setCustomer(dto.clienteDTO() != null ? customerService.buscarClienteID(dto.clienteDTO().id()) : orderEntity.getCustomer());
        orderEntity.setStatusPedido(dto.statusPedido() != null ? dto.statusPedido() : orderEntity.getStatusPedido());
        orderRespositoryPort.save(orderEntity);

        return orderEntity;
    }

    @Override
    public List<Order> findAll() {
        return orderRespositoryPort.findAll();
    }
}
