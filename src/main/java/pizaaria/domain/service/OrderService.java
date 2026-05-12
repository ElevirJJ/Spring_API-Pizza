package pizaaria.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizaaria.domain.dto.OrderDTO;
import pizaaria.domain.entity.Order;
import pizaaria.domain.exception.NotFoundException;
import pizaaria.domain.status.StatusOrder;
import pizaaria.repository.OrderRepository;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository pedidosRepository;
    private final PizzaService pizzaService;
    private final CustomerService clienteService;


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
                .build();

        pedidosRepository.saveAndFlush(newPedido);
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
