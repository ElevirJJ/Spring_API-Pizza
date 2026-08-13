package pizaaria.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.domain.dto.OrderDTO;
import pizaaria.domain.entity.Order;
import pizaaria.domain.service.OrderService;


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
    public Page<OrderDTO> get (Pageable pageable){
       return pedidoService.get(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> getId (@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.buscarId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity <Void> put (@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        pedidoService.update(id, orderDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id){
        pedidoService.deleteId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
