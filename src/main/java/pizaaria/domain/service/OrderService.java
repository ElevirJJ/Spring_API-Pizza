package pizaaria.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizaaria.domain.dto.CustomerDTO;
import pizaaria.domain.dto.OrderDTO;
import pizaaria.domain.dto.PizzaDTO;
import pizaaria.domain.entity.Order;
import pizaaria.domain.exception.NotFoundException;
import pizaaria.domain.message.event.OrderEvent;
import pizaaria.domain.message.produce.OrderProduce;
import pizaaria.domain.status.StatusOrder;
import pizaaria.repository.OrderRepository;


import java.time.LocalDateTime;
import java.util.List;

@Service

public class OrderService {

    private final OrderRepository pedidosRepository;
    private final PizzaService pizzaService;
    private final CustomerService clienteService;
    private final OrderProduce orderProduce;

    public OrderService(OrderRepository pedidosRepository, PizzaService pizzaService, CustomerService clienteService, OrderProduce orderProduce) {
        this.pedidosRepository = pedidosRepository;
        this.pizzaService = pizzaService;
        this.clienteService = clienteService;
        this.orderProduce = orderProduce;
    }


    public void createOrder(OrderDTO dto) {


        var pizzaPost = pizzaService.buscarPorIdPizza(dto.pizzaDTO().id());
        var customerPost = clienteService.buscarClienteID(dto.clienteDTO().id());

        var status = dto.statusPedido() != null ? dto.statusPedido() : StatusOrder.PENDENTE;
        var data = dto.dataPedido() != null ? dto.dataPedido() : LocalDateTime.now();

        Order newPedido = Order.builder()
                .dataPedido(data)
                .statusPedido(status)
                .pizza(pizzaPost)
                .customer(customerPost)
                .ativo(true)
                .build();

         var orderSalvo = pedidosRepository.save(newPedido);

        PizzaDTO pizzaDTO = new PizzaDTO(
                pizzaPost.getId(),
                pizzaPost.getNome(),
                pizzaPost.getDescricao()
        );

        CustomerDTO customerDTO = new CustomerDTO(
                customerPost.getId(),
                customerPost.getNome(),
                customerPost.getTelefone(),
                customerPost.getEndereco()
        );

         OrderEvent event = new OrderEvent(
                orderSalvo.getId(),
                customerDTO,
                pizzaDTO,
                orderSalvo.getStatusPedido(),
                orderSalvo.getDataPedido()
        );

        orderProduce.enviarOrder(event);

    }

    public List<Order> get (){
       return pedidosRepository.findAll();
    }

    public Order buscarId (Long id){
        return pedidosRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("pedido nao encontrado"));
    }

    public void deleteId (Long id){
        var orderDelete = buscarId(id);
        orderDelete.setAtivo(false);
        pedidosRepository.save(orderDelete);
    }

    @Transactional
    public void update(Long id, OrderDTO orderDTO) {

        Order orderEntity = buscarId(id);

        orderEntity.setDataPedido(orderDTO.dataPedido() != null ? orderDTO.dataPedido() : orderEntity.getDataPedido());
        orderEntity.setStatusPedido(orderDTO.statusPedido() != null ? orderDTO.statusPedido() : orderEntity.getStatusPedido());
        orderEntity.setId(orderEntity.getId());

        pedidosRepository.saveAndFlush(orderEntity);
    }





}
