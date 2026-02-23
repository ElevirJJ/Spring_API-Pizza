package pizaaria.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizaaria.domain.dto.CustomerDTO;
import pizaaria.domain.dto.OrderDTO;
import pizaaria.domain.dto.PizzaDTO;
import pizaaria.domain.entity.Customer;
import pizaaria.domain.entity.Order;
import pizaaria.domain.entity.Pizza;
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


    public void createPedido(OrderDTO dto) {
        var pizzaPost = pizzaService.buscarPorIdPizza(dto.pizzaDTO().id());
        var customerPost = clienteService.buscarClienteID(dto.clienteDTO().id());


        if (customerPost.getTelefone() == null || customerPost.getTelefone().isBlank()){
            throw new RuntimeException("O cliente precisa preecnher o telefone");
        }

        var status = dto.statusPedido() != null ? dto.statusPedido() : StatusOrder.PENDENTE;
        var data = dto.dataPedido() != null ? dto.dataPedido() : LocalDateTime.now();

        Order newPedido = Order.builder()
                .dataPedido(data)
                .statusPedido(status)
                .pizza(pizzaPost)
                .cliente(customerPost)
                .build();

        pedidosRepository.saveAndFlush(newPedido);
    }

    public List<Order> get (){
       return pedidosRepository.findAll();
    }

    public Order buscarId (Long id){
        return pedidosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("pedido não encontrado"));
    }

    public void deleteId (Long id){
        if (pedidosRepository.existsById(id)){
            pedidosRepository.deleteById(id);
        }else {
            throw new RuntimeException(id + "não existir no banco de dados");
        }
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
