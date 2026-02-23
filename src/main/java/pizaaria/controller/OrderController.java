package pizaaria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.domain.dto.OrderDTO;
import pizaaria.domain.entity.Order;
import pizaaria.domain.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService pedidoService;

    @PostMapping
    public ResponseEntity<Void> Post (@RequestBody OrderDTO pizzaDTO){
        pedidoService.createPedido(pizzaDTO);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity <List<Order>> get (){
        var list = pedidoService.get();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getId (@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.buscarId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity <Void> put (@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        pedidoService.update(id, orderDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id){
        pedidoService.deleteId(id);
        return ResponseEntity.ok().build();
    }
}
