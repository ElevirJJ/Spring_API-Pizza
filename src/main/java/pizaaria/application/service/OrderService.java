package pizaaria.application.service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pizaaria.adapters.in.web.dto.CustomerDTO;
import pizaaria.adapters.in.web.dto.OrderDTO;
import pizaaria.adapters.in.web.dto.PizzaDTO;
import pizaaria.domain.entityDominio.Order;
import pizaaria.adapters.out.persistence.broker.event.OrderEvent;
import pizaaria.adapters.out.persistence.broker.produce.OrderProduce;
import pizaaria.domain.exception.NotFoundException;
import pizaaria.domain.port.in.OrderCase;
import pizaaria.domain.port.out.OrderRespositoryPort;
import pizaaria.domain.status.StatusOrder;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderCase {

    private final PizzaService pizzaService;
    private final CustomerService customerService;
    private final OrderProduce orderProduce;
    private final OrderRespositoryPort orderRespositoryPort;
    private final ModelMapper modelMapper;

    public OrderService(PizzaService pizzaService, CustomerService customerService, OrderProduce orderProduce, OrderRespositoryPort orderRespositoryPort, ModelMapper modelMapper) {
        this.pizzaService = pizzaService;
        this.customerService = customerService;
        this.orderProduce = orderProduce;
        this.orderRespositoryPort = orderRespositoryPort;
        this.modelMapper = modelMapper;
    }


    @Override
    public Order createOrder(OrderDTO orderDTO) {
        var pizzaPost = pizzaService.getById(orderDTO.getPizzaDTO().getId());
        var customPost = customerService.getById(orderDTO.getClienteDTO().getId());

        var status = orderDTO.getStatusPedido() != null ? orderDTO.getStatusPedido() : StatusOrder.PENDENTE;
        var date = orderDTO.getDataPedido() != null ? orderDTO.getDataPedido() : LocalDateTime.now();

        Order newOrder = Order.builder()
                .pizza(pizzaPost)
                .customer(customPost)
                .statusPedido(status)
                .dataPedido(date)
                .ativo(true)
                .build();

        var orderSave = orderRespositoryPort.save(newOrder);


        CustomerDTO customerDTO = modelMapper.map(
                orderSave.getCustomer(),
                CustomerDTO.class
        );

        PizzaDTO pizzaDTO = modelMapper.map(
                orderSave.getPizza(),
                PizzaDTO.class
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
    public Order getById(Long id) {
        return orderRespositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
    }

    @Override
    public List<Order> findAll() {
        return orderRespositoryPort.findAll();
    }

    @Override
    public Order update(Long id, OrderDTO orderDTO) {


        var orderEntity = getById(id);

        orderEntity.setDataPedido(orderDTO.getDataPedido() != null ? orderDTO.getDataPedido() : orderEntity.getDataPedido());
        orderEntity.setStatusPedido(orderDTO.getStatusPedido() != null ? orderDTO.getStatusPedido() : orderEntity.getStatusPedido());
        orderEntity.setCustomer(orderDTO.getClienteDTO() != null ? customerService.getById(orderDTO.getClienteDTO().getId()) : orderEntity.getCustomer());
        orderEntity.setPizza(orderDTO.getPizzaDTO() != null ? pizzaService.getById(orderDTO.getPizzaDTO().getId()) : orderEntity.getPizza());
        orderEntity.setId(orderEntity.getId());

        return orderRespositoryPort.save(orderEntity);
    }
}
