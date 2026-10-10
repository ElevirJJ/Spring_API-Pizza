package pizaaria.adapters.in.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.adapters.in.web.dto.OrderDTO;
import pizaaria.application.service.OrderService;
import pizaaria.domain.entityDominio.Order;

import java.util.List;


@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService pedidoService;

    @PostMapping
    public ResponseEntity<Void> Post (@Valid @RequestBody OrderDTO pizzaDTO){
        pedidoService.createOrder(pizzaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Order>> get (){
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <Order> getId (@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.getById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity <Void> put (@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        pedidoService.update(id, orderDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id){
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
