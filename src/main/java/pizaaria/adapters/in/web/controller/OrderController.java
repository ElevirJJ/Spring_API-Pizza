package pizaaria.adapters.in.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD:src/main/java/pizaaria/adapters/in/web/controller/OrderController.java
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> dc391396d247377713b0040804df70147c5a1e60:src/main/java/pizaaria/controller/OrderController.java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizaaria.adapters.in.web.dto.OrderDTO;
import pizaaria.application.service.OrderService;
import pizaaria.domain.entityDominio.Order;

<<<<<<< HEAD:src/main/java/pizaaria/adapters/in/web/controller/OrderController.java
import java.util.List;
import java.util.Optional;
=======
>>>>>>> dc391396d247377713b0040804df70147c5a1e60:src/main/java/pizaaria/controller/OrderController.java

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> Post (@Valid @RequestBody OrderDTO pizzaDTO){
        pedidoService.createOrder(pizzaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
<<<<<<< HEAD:src/main/java/pizaaria/adapters/in/web/controller/OrderController.java
    public ResponseEntity <List<Order>> get (){
        var list = pedidoService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> getId (@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.getById(id));
=======
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderDTO> get (Pageable pageable){
       return pedidoService.get(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> getId (@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.buscarId(id));
>>>>>>> dc391396d247377713b0040804df70147c5a1e60:src/main/java/pizaaria/controller/OrderController.java
    }


    @PutMapping("/{id}")
    public ResponseEntity <Void> put (@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        pedidoService.update(id, orderDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id){
<<<<<<< HEAD:src/main/java/pizaaria/adapters/in/web/controller/OrderController.java
        pedidoService.deleteById(id);
        return ResponseEntity.ok().build();
=======
        pedidoService.deleteId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
>>>>>>> dc391396d247377713b0040804df70147c5a1e60:src/main/java/pizaaria/controller/OrderController.java
    }
}
