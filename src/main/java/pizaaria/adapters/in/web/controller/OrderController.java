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
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> Post (@Valid @RequestBody OrderDTO pizzaDTO){
        pedidoService.createOrder(pizzaDTO);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity <List<Order>> get (){
        var list = pedidoService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> getId (@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.getById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity <Void> put (@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        pedidoService.update(id, orderDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id){
        pedidoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
